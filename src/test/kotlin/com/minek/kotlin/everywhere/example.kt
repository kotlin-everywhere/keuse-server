package com.minek.kotlin.everywhere

import com.minek.kotlin.everywhere.kelibs.result.Err
import com.minek.kotlin.everywhere.kelibs.result.Ok
import com.minek.kotlin.everywhere.kelibs.result.andThen
import com.minek.kotlin.everywhere.keuse.Crate
import com.minek.kotlin.everywhere.keuse.runServer
import com.minek.kotline.everywehre.keuson.decode.Decoder
import com.minek.kotline.everywehre.keuson.decode.Decoders
import com.minek.kotline.everywehre.keuson.decode.map
import com.minek.kotline.everywehre.keuson.encode.Encoder
import com.minek.kotline.everywehre.keuson.encode.Encoders
import java.util.*

private val uuidEncoder: Encoder<UUID> = { Encoders.string(it.toString()) }
private val uuidDecoder: Decoder<UUID> = {
    Decoders.string(it).andThen {
        try {
            Ok<String, UUID>(UUID.fromString(it))
        } catch (e: Exception) {
            Err<String, UUID>(e.message ?: e.toString())
        }
    }
}

class Example : Crate() {
    val echo by e(Decoders.int, Encoders.int)
    val greet by e(Decoders.string, Encoders.string)
    val todo by c(::TodoCrate)
}

class TodoCrate : Crate() {
    val add by e(Todo.decoder, Add.encoder)
    val list by e(Decoders.nullable(Decoders.string), { todoList: List<Todo> -> Encoders.array(todoList.map { Todo.encoder(it) }) })
    val update by e(Todo.decoder, Encoders.bool)
    val delete by e(uuidDecoder, Encoders.bool)

    sealed class Add {
        object Success : Add()
        object DuplicatedId : Add()

        companion object {
            val encoder = { it: Add ->
                Encoders.string(it.javaClass.simpleName)
            }
        }
    }

    data class Todo(val id: UUID, val title: String) {
        companion object {
            val decoder = map(Decoders.field("id", uuidDecoder), Decoders.field("title", Decoders.string), ::Todo)
            val encoder = { (id, title): Todo ->
                Encoders.object_("id" to uuidEncoder(id), "title" to Encoders.string(title))
            }
        }
    }
}

fun Example.impl() {
    echo { it }
    greet { "Hello, $it!" }
    todo.impl()
}

fun TodoCrate.impl() {
    var jar = listOf<TodoCrate.Todo>()

    add { newTodo ->
        if (jar.has(newTodo.id)) {
            TodoCrate.Add.DuplicatedId
        } else {
            jar += newTodo
            TodoCrate.Add.Success
        }
    }

    list { keyword ->
        if (keyword != null) {
            jar.filter { it.title.contains(keyword) }
        } else {
            jar
        }
    }

    update { todo ->
        if (jar.has(todo.id)) {
            jar = jar.map { if (it.id == todo.id) todo else it }
            true
        } else {
            false
        }
    }

    delete { id ->
        if (jar.has(id)) {
            jar = jar.filter { it.id != id }
            true
        } else {
            false
        }
    }
}

private fun List<TodoCrate.Todo>.has(id: UUID): Boolean {
    return firstOrNull { it.id == id } != null
}

fun main(args: Array<String>) {
    val example = Example().apply { impl() }

    example.runServer(5000)
}

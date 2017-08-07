package com.minek.kotlin.everywhere

import com.minek.kotlin.everywhere.kelibs.result.Err
import com.minek.kotlin.everywhere.kelibs.result.Ok
import com.minek.kotlin.everywhere.kelibs.result.andThen
import com.minek.kotlin.everywhere.keuse.Crate
import com.minek.kotlin.everywhere.keuse.runServer
import com.minek.kotline.everywehre.keuson.convert.Converters.boolean
import com.minek.kotline.everywehre.keuson.convert.Converters.int
import com.minek.kotline.everywehre.keuson.convert.Converters.list
import com.minek.kotline.everywehre.keuson.convert.Converters.nullable
import com.minek.kotline.everywehre.keuson.convert.Converters.string
import com.minek.kotline.everywehre.keuson.decode.Decoder
import com.minek.kotline.everywehre.keuson.decode.Decoders
import com.minek.kotline.everywehre.keuson.decode.Decoders.fail
import com.minek.kotline.everywehre.keuson.decode.Decoders.success
import com.minek.kotline.everywehre.keuson.decode.andThen
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
private val uuidConverter = uuidEncoder to uuidDecoder

class Example : Crate() {
    val echo by e(int, int)
    val greet by e(string, string)
    val todo by c(::TodoCrate)
}

class TodoCrate : Crate() {
    val add by e(Todo.converter, Add.converter)
    val list by e(nullable(string), list(Todo.converter))
    val update by e(Todo.converter, boolean)
    val delete by e(uuidConverter, boolean)

    sealed class Add {
        object Success : Add()
        object DuplicatedId : Add()

        companion object {
            val encoder = { it: Add ->
                when (it) {
                    Success -> Encoders.string("Success")
                    DuplicatedId -> Encoders.string("DuplicatedId")
                }
            }
            val decoder = andThen(Decoders.string) {
                when (it) {
                    "Success" -> success(Success)
                    "DuplicatedId" -> success(DuplicatedId)
                    else -> fail("$it is invalid Add Type")
                }
            }
            val converter = encoder to decoder
        }
    }

    data class Todo(val id: UUID, val title: String) {
        companion object {
            val decoder = map(Decoders.field("id", uuidDecoder), Decoders.field("title", Decoders.string), ::Todo)
            val encoder = { (id, title): Todo ->
                Encoders.object_("id" to uuidEncoder(id), "title" to Encoders.string(title))
            }
            val converter = encoder to decoder
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

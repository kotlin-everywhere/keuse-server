package com.minek.kotlin.everywhere.keuse

import com.github.kotlin.everywhere.json.decode.Decoder
import com.github.kotlin.everywhere.json.encode.Encoder
import com.minek.kotlin.everywhere.keuse.Box
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class Crate {
    private var boxes = mapOf<String, Box<*, *>>()
    private var crates = mapOf<String, Crate>()

    fun <P, R> b(decoder: Decoder<P>, encoder: Encoder<R>): Box.BoxDelegate<P, R> {
        return Box.BoxDelegate(decoder, encoder) { name, box ->
            boxes += name to box
        }
    }

    fun <S : Crate> c(constructor: () -> S): Delegate<S> {
        return Delegate(constructor) { name, crate -> crates += name to crate }
    }

    internal fun findBox(path: String): Box<*, *>? {
        return findBox(path.split('/'))
    }

    private fun findBox(paths: List<String>): Box<*, *>? {
        if (paths.size == 1) {
            val path = paths.first()
            return boxes[path]
        }
        return crates[paths.first()]?.findBox(paths.subList(1, paths.size))
    }

    class Delegate<out T : Crate>(private val constructor: () -> T, private val attach: (name: String, crate: T) -> Unit) : ReadOnlyProperty<Crate, T> {
        private var crate = null as T?

        override fun getValue(thisRef: Crate, property: KProperty<*>): T {
            return crate ?: constructor().apply { crate = this; attach(property.name, this) }
        }
    }
}

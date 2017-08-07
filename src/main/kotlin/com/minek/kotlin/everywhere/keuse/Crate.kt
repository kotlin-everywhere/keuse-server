package com.minek.kotlin.everywhere.keuse

import com.minek.kotline.everywehre.keuson.decode.Decoder
import com.minek.kotline.everywehre.keuson.encode.Encoder
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class Crate {
    private var endPoints = mapOf<String, EndPoint<*, *>>()
    private var crates = mapOf<String, Crate>()

    fun <P, R> e(decoder: Decoder<P>, encoder: Encoder<R>): EndPoint.BoxDelegate<P, R> {
        return EndPoint.BoxDelegate(decoder, encoder) { name, box ->
            endPoints += name to box
        }
    }

    fun <S : Crate> c(constructor: () -> S): Delegate<S> {
        return Delegate(constructor) { name, crate -> crates += name to crate }
    }

    internal fun findBox(path: String): EndPoint<*, *>? {
        return findBox(path.split('/'))
    }

    private fun findBox(paths: List<String>): EndPoint<*, *>? {
        if (paths.size == 1) {
            val path = paths.first()
            return endPoints[path]
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

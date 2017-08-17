package com.minek.kotlin.everywhere.keuse

import com.google.gson.JsonElement
import com.minek.kotlin.everywhere.kelibs.result.Result
import com.minek.kotlin.everywhere.kelibs.result.map
import com.minek.kotlin.everywehre.keuson.convert.Converter
import com.minek.kotlin.everywehre.keuson.convert.decoder
import com.minek.kotlin.everywehre.keuson.convert.encoder
import com.minek.kotlin.everywehre.keuson.decode.Decoder
import com.minek.kotlin.everywehre.keuson.encode.Encoder
import com.minek.kotlin.everywehre.keuson.encode.Value
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class Crate {
    private var endPoints = mapOf<String, EndPoint<*, *>>()
    private var crates = mapOf<String, Crate>()

    fun <P, R> e(parameterConvert: Converter<P>, resultConverter: Converter<R>): EndPoint.BoxDelegate<P, R> {
        return EndPoint.BoxDelegate(parameterConvert, resultConverter) { name, box ->
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



typealias Handler<P, R> = (P) -> R

class EndPoint<P, R>(private val decoder: Decoder<P>, private val encoder: Encoder<R>) {
    var handler: Handler<P, R> = { throw NotImplementedError() }
        private set

    operator fun invoke(handler: Handler<P, R>) {
        this.handler = handler
    }

    class BoxDelegate<P, R>(private val parameterConverter: Converter<P>, private val resultConverter: Converter<R>, private val attach: (name: String, endPoint: EndPoint<P, R>) -> Unit) : ReadOnlyProperty<Crate, EndPoint<P, R>> {
        private var box = null as EndPoint<P, R>?

        override fun getValue(thisRef: Crate, property: KProperty<*>): EndPoint<P, R> {
            return box ?: EndPoint(parameterConverter.decoder, resultConverter.encoder).apply { box = this; attach(property.name, this) }
        }
    }

    internal fun handle(inputElement: JsonElement): Result<String, Value> {
        return decoder(inputElement).map(handler).map(encoder)
    }
}
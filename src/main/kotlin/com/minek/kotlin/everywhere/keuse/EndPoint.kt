package com.minek.kotlin.everywhere.keuse

import com.google.gson.JsonElement
import com.minek.kotlin.everywhere.kelibs.result.Result
import com.minek.kotlin.everywhere.kelibs.result.map
import com.minek.kotline.everywehre.keuson.decode.Decoder
import com.minek.kotline.everywehre.keuson.encode.Encoder
import com.minek.kotline.everywehre.keuson.encode.Value
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

typealias Handler<P, R> = (P) -> R

class EndPoint<P, R>(private val decoder: Decoder<P>, private val encoder: Encoder<R>) {
    var handler: Handler<P, R> = { throw NotImplementedError() }
        private set

    operator fun invoke(handler: Handler<P, R>) {
        this.handler = handler
    }

    class BoxDelegate<P, R>(private val decoder: Decoder<P>, private val encoder: Encoder<R>, private val attach: (name: String, endPoint: EndPoint<P, R>) -> Unit) : ReadOnlyProperty<Crate, EndPoint<P, R>> {
        private var box = null as EndPoint<P, R>?

        override fun getValue(thisRef: Crate, property: KProperty<*>): EndPoint<P, R> {
            return box ?: EndPoint(decoder, encoder).apply { box = this; attach(property.name, this) }
        }
    }

    internal fun handle(inputElement: JsonElement): Result<String, Value> {
        return decoder(inputElement).map(handler).map(encoder)
    }
}
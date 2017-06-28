package com.github.kotlin.everywhere.server

import com.github.kotlin.everywhere.json.decode.Decoder
import com.github.kotlin.everywhere.json.decode.Result
import com.github.kotlin.everywhere.json.encode.Encoder
import com.github.kotlin.everywhere.json.encode.Value
import com.google.gson.JsonElement
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

typealias Handler<P, R> = (P) -> R

class Box<P, R>(private val decoder: Decoder<P>, private val encoder: Encoder<R>) {
    var handler: Handler<P, R> = { throw NotImplementedError() }
        private set

    operator fun invoke(handler: Handler<P, R>) {
        this.handler = handler
    }

    class BoxDelegate<P, R>(private val decoder: Decoder<P>, private val encoder: Encoder<R>, private val attach: (name: String, box: Box<P, R>) -> Unit) : ReadOnlyProperty<Crate, Box<P, R>> {
        private var box = null as Box<P, R>?

        override fun getValue(thisRef: Crate, property: KProperty<*>): Box<P, R> {
            return box ?: Box<P, R>(decoder, encoder).apply { box = this; attach(property.name, this) }
        }
    }

    internal fun handle(inputElement: JsonElement): Result<String, Value> {
        return decoder(inputElement).map(handler).map(encoder)
    }
}
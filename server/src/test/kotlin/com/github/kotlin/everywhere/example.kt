package com.github.kotlin.everywhere

import com.github.kotlin.everywhere.json.decode.Decoder
import com.github.kotlin.everywhere.json.encode.Value
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import com.github.kotlin.everywhere.json.decode.Decoders as d
import com.github.kotlin.everywhere.json.encode.Encoders as e

typealias Encoder<T> = (T) -> Value



class Handler<P, R>(private val crate: Crate, private val name: String, private val decoder: Decoder<P>, private val encoder: Encoder<R>) {
    private lateinit var handle: (P) -> R

    operator fun invoke(handle: (P) -> R): Unit {
        this.handle = handle
    }

    class Delegate<P, R>(private val decoder: Decoder<P>, private val encoder: Encoder<R>) : ReadOnlyProperty<Crate, Handler<P, R>> {
        internal var handler: Handler<P, R>? = null
            private set

        override fun getValue(thisRef: Crate, property: KProperty<*>): Handler<P, R> {
            val handler = this.handler
            if (handler != null) {
                return handler
            }
            return Handler(thisRef, property.name, decoder, encoder).apply { this@Delegate.handler = this }
        }
    }
}


abstract class Crate {
    private var handlerDelegates = listOf<Handler.Delegate<*, *>>()
    private var createDelegates = listOf<Delegate<*>>()
    private var parent = null as Parent?

    fun <P, R> f(decoder: Decoder<P>, encoder: Encoder<R>): Handler.Delegate<P, R> {
        val delegate = Handler.Delegate(decoder, encoder)
        handlerDelegates += delegate
        return delegate
    }

    fun <T : Crate> f(crate: T): Delegate<T> {
        return Delegate(crate).apply { createDelegates += this }
    }

    class Delegate<out T : Crate>(private val crate: T) : ReadOnlyProperty<Crate, T> {
        private var initialized = false

        override fun getValue(thisRef: Crate, property: KProperty<*>): T {
            if (!initialized) {
                crate.parent = Parent(property.name, thisRef)
                initialized = true
            }
            return crate
        }
    }

    private class Parent(val name: String, val crate: Crate)
}

class Calc : Crate() {
    val halfIt by f(d.float, e.float)
}

class Root : Crate() {
    val doubleIt by f(d.int, e.int)
    val calc by f(Calc())
}

fun Root.impl() {
    doubleIt {
        it * 2
    }

    calc.impl()
}

fun Calc.impl() {
    halfIt {
        it / 2
    }
}

fun main(args: Array<String>) {
    Root().apply { impl() }
}

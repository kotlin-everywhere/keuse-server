package com.github.kotlin.everywhere.server

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

typealias Handler<P, R> = (P) -> R

class Box<P, R> {
    var handler: Handler<P, R> = { throw NotImplementedError() }
        private set

    operator fun invoke(handler: Handler<P, R>) {
        this.handler = handler
    }

    class BoxDelegate<P, R> : ReadOnlyProperty<Crate, Box<P, R>> {
        private var box = null as Box<P, R>?

        override fun getValue(thisRef: Crate, property: KProperty<*>): Box<P, R> {
            return box ?: Box<P, R>().apply { box = this }
        }
    }
}
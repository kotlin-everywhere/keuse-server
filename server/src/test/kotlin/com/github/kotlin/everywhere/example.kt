package com.github.kotlin.everywhere

import com.github.kotlin.everywhere.json.decode.Decoder
import com.github.kotlin.everywhere.json.decode.Decoders.int

class Handler<P, R>(val decoder: Decoder<P>) {
    operator fun invoke(handle: (P) -> R): Unit {
    }
}

abstract class Crate {
    fun <P, R> f(decoder: Decoder<P>): Handler<P, R> {
        return Handler(decoder)
    }
}


class Root : Crate() {
    val doubleIt = f<Int, Int>(int)
}

fun Root.impl() {
    doubleIt {
        it * 2
    }
}

fun main(args: Array<String>) {
    Root().apply { impl() }
}

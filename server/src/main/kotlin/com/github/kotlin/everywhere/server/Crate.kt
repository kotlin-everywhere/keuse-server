package com.github.kotlin.everywhere.server

import com.github.kotlin.everywhere.server.Box.BoxDelegate

abstract class Crate {
    fun <P, R> f(): BoxDelegate<P, R> {
        return BoxDelegate()
    }
}
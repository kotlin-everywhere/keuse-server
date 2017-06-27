package com.github.kotlin.everywhere

import com.github.kotlin.everywhere.server.Crate
import org.junit.Assert.assertEquals
import org.junit.Test

class TestBox {
    @Test
    fun testHandler() {
        val crate = object : Crate() {
            val echo by f<String, String>()

            init {
                echo { it }
            }
        }

        assertEquals("hello", crate.echo.handler("hello"))
    }
}
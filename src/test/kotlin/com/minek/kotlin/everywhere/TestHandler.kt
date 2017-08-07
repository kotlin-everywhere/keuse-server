package com.minek.kotlin.everywhere

import com.github.kotlin.everywhere.json.decode.Decoders
import com.github.kotlin.everywhere.json.encode.Encoders
import com.minek.kotlin.everywhere.keuse.Crate
import org.junit.Assert.assertEquals
import org.junit.Test

class TestBox {
    @Test
    fun testHandler() {
        val crate = object : Crate() {
            val echo by b(Decoders.string, Encoders.string)
            val doubleIt by b(Decoders.int, Encoders.int)

            init {
                echo { it }
                doubleIt { it * 2 }
            }
        }

        assertEquals("hello", crate.echo.handler("hello"))
        assertEquals(4, crate.doubleIt.handler(2))
    }
}
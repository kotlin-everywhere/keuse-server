package com.minek.kotlin.everywhere

import com.minek.kotlin.everywhere.keuse.Crate
import com.minek.kotline.everywehre.keuson.decode.Decoders
import com.minek.kotline.everywehre.keuson.encode.Encoders
import org.junit.Assert.assertEquals
import org.junit.Test

class TestEndPoint {
    @Test
    fun testHandler() {
        val crate = object : Crate() {
            val echo by e(Decoders.string, Encoders.string)
            val doubleIt by e(Decoders.int, Encoders.int)

            init {
                echo { it }
                doubleIt { it * 2 }
            }
        }

        assertEquals("hello", crate.echo.handler("hello"))
        assertEquals(4, crate.doubleIt.handler(2))
    }
}
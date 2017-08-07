package com.minek.kotlin.everywhere

import com.minek.kotlin.everywhere.keuse.Crate
import com.minek.kotline.everywehre.keuson.convert.Converters.int
import com.minek.kotline.everywehre.keuson.convert.Converters.string
import org.junit.Assert.assertEquals
import org.junit.Test

class TestEndPoint {
    @Test
    fun testHandler() {
        val crate = object : Crate() {
            val echo by e(string, string)
            val doubleIt by e(int, int)

            init {
                echo { it }
                doubleIt { it * 2 }
            }
        }

        assertEquals("hello", crate.echo.handler("hello"))
        assertEquals(4, crate.doubleIt.handler(2))
    }
}
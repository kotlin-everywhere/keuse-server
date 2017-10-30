package com.minek.kotlin.everywhere

import com.minek.kotlin.everywehre.keuson.convert.Converters
import com.minek.kotlin.everywehre.keuson.convert.Converters.int
import com.minek.kotlin.everywehre.keuson.convert.Converters.string
import com.minek.kotlin.everywhere.keuse.Crate
import com.minek.kotlin.everywhere.keuse.HttpEnvironment
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

    @Test
    fun testHttpEndpoint() {
        val crate = object : Crate() {
            val echo by e(Converters.string, Converters.string)
            val multiple by e(Converters.int, Converters.int)

            init {
                echo.withHttpEnv { e, s -> "${e.host} - $s" }
                multiple.withHttpEnv { e, i -> e.host.length * i }
            }
        }

        val environment = HttpEnvironment(host = "example.com")
        assertEquals("example.com - hello", crate.echo.httpHandler(environment, "hello"))
        assertEquals(22, crate.multiple.httpHandler(environment, 2))
    }
}
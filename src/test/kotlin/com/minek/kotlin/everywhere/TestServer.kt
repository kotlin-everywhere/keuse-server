package com.minek.kotlin.everywhere

import com.github.kittinunf.fuel.httpPost
import com.minek.kotlin.everywehre.keuson.convert.Converters.string
import com.minek.kotlin.everywhere.keuse.Crate
import com.minek.kotlin.everywhere.keuse.runServer
import org.junit.Assert
import org.junit.Test
import javax.servlet.*

class Root : Crate() {
    val echo by e(string, string)
}

fun Root.impl() {
    echo { it }
}

class TestServer {
    @Test
    fun testIntegration() {
        Root().apply(Root::impl).runServer { port, _ ->
            val response =
                    "http://localhost:$port/echo".httpPost().body("\"hello\"".toByteArray()).responseString()
            Assert.assertEquals("\"hello\"", String(response.second.data))
        }
    }

    @Test
    fun testFilter() {
        val filter = object : Filter {
            override fun destroy() {}

            override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
                response?.writer?.write("filtered")
            }

            override fun init(filterConfig: FilterConfig?) {}
        }
        Root().apply(Root::impl).runServer(filters = listOf(filter)) { port, _ ->
            val response =
                    "http://localhost:$port/echo".httpPost().body("\"hello\"".toByteArray()).responseString()
            Assert.assertEquals("filtered", String(response.second.data))
        }
    }
}
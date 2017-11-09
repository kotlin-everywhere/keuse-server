package com.minek.kotlin.everywhere

import com.github.kittinunf.fuel.httpPost
import com.minek.kotlin.everywehre.keuson.convert.Converters.nullable
import com.minek.kotlin.everywehre.keuson.convert.Converters.result
import com.minek.kotlin.everywehre.keuson.convert.Converters.string
import com.minek.kotlin.everywhere.kelibs.result.Err
import com.minek.kotlin.everywhere.kelibs.result.Ok
import com.minek.kotlin.everywhere.keuse.Crate
import com.minek.kotlin.everywhere.keuse.runServer
import org.junit.Assert
import org.junit.Test
import javax.servlet.*
import javax.servlet.http.HttpServletRequest

class Root : Crate() {
    val echo by e(string, string)
    val nullEcho by e(nullable(string), result(string, nullable(string)))
}

fun Root.impl() {
    echo { it }

    nullEcho {
        if (it == null) Ok(null)
        else Err("not null")
    }
}

class TestServer {
    @Test
    fun testIntegration() {
        Root().apply(Root::impl).runServer { port, _ ->

            val echoResponse = "http://localhost:$port/echo".httpPost().body("\"hello\"".toByteArray()).responseString()
            Assert.assertEquals("\"hello\"", String(echoResponse.second.data))

            // 내부적으로 가지고 있는 Gson 사용핧경우 Converter 와 설정이 달라서 예상치 못한 결과가 나온다.
            // 대표적으로 {value: null} 일경우 Gson 기본값은 {} 으로 출력 된다.
            val nullEcho = "http://localhost:$port/nullEcho".httpPost()
            val notNullEchoResponse = nullEcho.body("\"hello\"".toByteArray()).responseString()
            Assert.assertEquals("""{"type":"Err","error":"not null"}""", String(notNullEchoResponse.second.data))
            val nullEchoResponse = nullEcho.body("null".toByteArray()).responseString()
            Assert.assertEquals("""{"type":"Ok","value":null}""", String(nullEchoResponse.second.data))
        }

        val httpEnvCrate = object : Crate() {
            val echo by e(string, string)

            init {
                echo.withHttpEnv { environment, s -> "${environment.host} - $s" }
            }
        }

        httpEnvCrate.runServer { port, _ ->
            val echoResponse = "http://localtest.me:$port/echo".httpPost().body("\"hello\"".toByteArray()).responseString()
            Assert.assertEquals("\"localtest.me:$port - hello\"", String(echoResponse.second.data))
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

    @Test
    fun testContextPath() {
        val filter = object : Filter {
            override fun destroy() {}

            override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
                response?.writer?.write("${(request as? HttpServletRequest)?.contextPath}")
            }

            override fun init(filterConfig: FilterConfig?) {}
        }
        val root = Root().apply(Root::impl)
        root.runServer(filters = listOf(filter)) { port, _ ->
            val response =
                    "http://localhost:$port/echo".httpPost().body("\"hello\"".toByteArray()).responseString()
            Assert.assertEquals("", String(response.second.data))
        }

        // testNestedContext
        root.runServer(contextPath = "/api") { port, _ ->
            val echoResponse = "http://localhost:$port/api/echo".httpPost().body("\"hello\"".toByteArray()).responseString()
            Assert.assertEquals("\"hello\"", String(echoResponse.second.data))
        }
    }
}
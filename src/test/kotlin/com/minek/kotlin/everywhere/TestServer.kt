package com.minek.kotlin.everywhere

import com.github.kittinunf.fuel.httpPost
import com.minek.kotlin.everywhere.keuse.Crate
import com.minek.kotlin.everywhere.keuse.runServer
import com.minek.kotline.everywehre.keuson.decode.Decoders
import com.minek.kotline.everywehre.keuson.encode.Encoders
import org.junit.Assert
import org.junit.Test

class Root : Crate() {
    val echo by e(Decoders.string, Encoders.string)
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
}
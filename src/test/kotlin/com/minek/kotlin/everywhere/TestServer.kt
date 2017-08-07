package com.minek.kotlin.everywhere

import com.github.kittinunf.fuel.httpPost
import com.github.kotlin.everywhere.json.decode.Decoders
import com.github.kotlin.everywhere.json.encode.Encoders
import com.minek.kotlin.everywhere.keuse.Crate
import com.minek.kotlin.everywhere.keuse.runServer
import org.junit.Assert
import org.junit.Test

class Root : Crate() {
    val echo by b(Decoders.string, Encoders.string)
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
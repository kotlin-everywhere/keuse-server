package com.github.kotlin.everywhere

import com.github.kotlin.everywhere.json.decode.Decoders.boolean
import com.github.kotlin.everywhere.json.decode.Decoders.string
import com.github.kotlin.everywhere.json.decode.Err
import com.github.kotlin.everywhere.json.decode.Ok
import com.github.kotlin.everywhere.json.decode.decodeString
import org.junit.Assert.assertEquals
import org.junit.Test

class DecoderTest {
    @Test
    fun testString() {
        assertEquals(Err.of("Expecting a String but instead got: true"), decodeString(string, "true"))
        assertEquals(Err.of("Expecting a String but instead got: 42"), decodeString(string, "42"))
        assertEquals(Err.of("Expecting a String but instead got: 3.14"), decodeString(string, "3.14"))
        assertEquals(Ok.of("hello"), decodeString(string, "\"hello\""))
        assertEquals(Err.of("Expecting a String but instead got: {\"hello\":42}"), decodeString(string, "{ \"hello\": 42 }"))
    }

    @Test
    fun testBoolean() {
        assertEquals(Ok.of(true), decodeString(boolean, "true"))
        assertEquals(Err.of("Expecting a Boolean but instead got: 42"), decodeString(boolean, "42"))
        assertEquals(Err.of("Expecting a Boolean but instead got: 3.14"), decodeString(boolean, "3.14"))
        assertEquals(Err.of("Expecting a Boolean but instead got: \"hello\""), decodeString(boolean, "\"hello\""))
        assertEquals(Err.of("Expecting a Boolean but instead got: {\"hello\":42}"), decodeString(boolean, "{ \"hello\": 42 }"))
    }
}

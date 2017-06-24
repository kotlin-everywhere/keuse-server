package com.github.kotlin.everywhere

import com.github.kotlin.everywhere.json.decode.Decoders.boolean
import com.github.kotlin.everywhere.json.decode.Decoders.float
import com.github.kotlin.everywhere.json.decode.Decoders.int
import com.github.kotlin.everywhere.json.decode.Decoders.nul
import com.github.kotlin.everywhere.json.decode.Decoders.nullable
import com.github.kotlin.everywhere.json.decode.Decoders.string
import com.github.kotlin.everywhere.json.decode.Err
import com.github.kotlin.everywhere.json.decode.Ok
import com.github.kotlin.everywhere.json.decode.decodeString
import org.junit.Assert.assertEquals
import org.junit.Test

class DecoderTest {
    @Test
    fun testString() {
        assertEquals(Err.of("Expecting a String but instead got: null"), decodeString(string, "null"))
        assertEquals(Err.of("Expecting a String but instead got: true"), decodeString(string, "true"))
        assertEquals(Err.of("Expecting a String but instead got: 42"), decodeString(string, "42"))
        assertEquals(Err.of("Expecting a String but instead got: 3.14"), decodeString(string, "3.14"))
        assertEquals(Ok.of("hello"), decodeString(string, "\"hello\""))
        assertEquals(Err.of("Expecting a String but instead got: {\"hello\":42}"), decodeString(string, "{ \"hello\": 42 }"))
    }

    @Test
    fun testBoolean() {
        assertEquals(Err.of("Expecting a Boolean but instead got: null"), decodeString(boolean, "null"))
        assertEquals(Ok.of(true), decodeString(boolean, "true"))
        assertEquals(Err.of("Expecting a Boolean but instead got: 42"), decodeString(boolean, "42"))
        assertEquals(Err.of("Expecting a Boolean but instead got: 3.14"), decodeString(boolean, "3.14"))
        assertEquals(Err.of("Expecting a Boolean but instead got: \"hello\""), decodeString(boolean, "\"hello\""))
        assertEquals(Err.of("Expecting a Boolean but instead got: {\"hello\":42}"), decodeString(boolean, "{ \"hello\": 42 }"))
    }

    @Test
    fun testInt() {
        assertEquals(Err.of("Expecting a Int but instead got: null"), decodeString(int, "null"))
        assertEquals(Err.of("Expecting a Int but instead got: true"), decodeString(int, "true"))
        assertEquals(Ok.of(42), decodeString(int, "42"))
        assertEquals(Err.of("Expecting a Int but instead got: 3.14"), decodeString(int, "3.14"))
        assertEquals(Err.of("Expecting a Int but instead got: \"hello\""), decodeString(int, "\"hello\""))
        assertEquals(Err.of("Expecting a Int but instead got: {\"hello\":42}"), decodeString(int, "{ \"hello\": 42 }"))
    }

    @Test
    fun testFloat() {
        assertEquals(Err.of("Expecting a Float but instead got: null"), decodeString(float, "null"))
        assertEquals(Err.of("Expecting a Float but instead got: true"), decodeString(float, "true"))
        assertEquals(Err.of("Expecting a Float but instead got: 42"), decodeString(float, "42"))
        assertEquals(Ok.of(3.14f), decodeString(float, "3.14"))
        assertEquals(Err.of("Expecting a Float but instead got: \"hello\""), decodeString(float, "\"hello\""))
        assertEquals(Err.of("Expecting a Float but instead got: {\"hello\":42}"), decodeString(float, "{ \"hello\": 42 }"))
    }

    @Test
    fun testNull() {
        val nullInt = nul<Int>()
        assertEquals(Ok.of(null), decodeString(nullInt, "null"))
        assertEquals(Err.of("Expecting a Null but instead got: true"), decodeString(nullInt, "true"))
        assertEquals(Err.of("Expecting a Null but instead got: 42"), decodeString(nullInt, "42"))
        assertEquals(Err.of("Expecting a Null but instead got: 3.14"), decodeString(nullInt, "3.14"))
        assertEquals(Err.of("Expecting a Null but instead got: \"hello\""), decodeString(nullInt, "\"hello\""))
        assertEquals(Err.of("Expecting a Null but instead got: {\"hello\":42}"), decodeString(nullInt, "{ \"hello\": 42 }"))
    }

    @Test
    fun testNullable() {
        assertEquals(Ok.of(null), decodeString(nullable(int), "null"))
        assertEquals(Err.of("Expecting a Int but instead got: true"), decodeString(int, "true"))
    }
}

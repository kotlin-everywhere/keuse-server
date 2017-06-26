package com.github.kotlin.everywhere

import com.github.kotlin.everywhere.json.encode.Encoders.bool
import com.github.kotlin.everywhere.json.encode.Encoders.float
import com.github.kotlin.everywhere.json.encode.Encoders.int
import com.github.kotlin.everywhere.json.encode.Encoders.string
import com.github.kotlin.everywhere.json.encode.encode
import org.junit.Assert.assertEquals
import org.junit.Test

class EncoderTest {
    @Test
    fun testString() {
        assertEquals("\"hello\"", encode(string("hello")))
    }

    @Test
    fun testInt() {
        assertEquals("42", encode(int(42)))
    }

    @Test
    fun testFloat() {
        assertEquals("3.14", encode(float(3.14f)))
    }

    @Test
    fun testBool() {
        assertEquals("false", encode(bool(false)))
    }
}
package com.github.kotlin.everywhere

import com.github.kotlin.everywhere.json.encode.Encoders.array
import com.github.kotlin.everywhere.json.encode.Encoders.bool
import com.github.kotlin.everywhere.json.encode.Encoders.float
import com.github.kotlin.everywhere.json.encode.Encoders.int
import com.github.kotlin.everywhere.json.encode.Encoders.object_
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

    @Test
    fun testObject() {
        assertEquals(
                """{"message":"hello","age":42}""",
                encode(object_("message" to string("hello"), "age" to int(42)))
        )
    }

    @Test
    fun testArray() {
        assertEquals("[1,2,3]", encode(array(listOf(1, 2, 3).map(::int))))
    }
}
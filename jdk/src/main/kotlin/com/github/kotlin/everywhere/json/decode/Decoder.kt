package com.github.kotlin.everywhere.json.decode

import com.google.gson.JsonElement
import com.google.gson.JsonParser

interface Decoder<T> {
    companion object {
        val string = object : Decoder<String> {
            override fun invoke(element: JsonElement): Result<String, String> {
                if (element.isJsonPrimitive) {
                    if (element.asJsonPrimitive.isString) {
                        return Ok(element.asString)
                    }
                }
                return Err("Expecting a String but instead got: $element")
            }
        }
    }

    operator fun invoke(element: JsonElement): Result<String, T>
}

fun <T> decodeString(decoder: Decoder<T>, json: String): Result<String, T> {
    return decoder(JsonParser().parse(json))
}
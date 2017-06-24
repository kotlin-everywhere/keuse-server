package com.github.kotlin.everywhere.json.decode

import com.google.gson.JsonElement
import com.google.gson.JsonParser

typealias Decoder<T> = (element: JsonElement) -> Result<String, T>

object Decoders {
    val string: Decoder<String> = {
        if (it.isJsonPrimitive && it.asJsonPrimitive.isString) {
            Ok(it.asString)
        } else {
            Err("Expecting a String but instead got: $it")
        }
    }
}

fun <T> decodeString(decoder: Decoder<T>, json: String): Result<String, T> {
    return decoder(JsonParser().parse(json))
}
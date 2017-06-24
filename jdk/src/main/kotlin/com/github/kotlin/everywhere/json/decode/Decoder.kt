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

    val boolean: Decoder<Boolean> = {
        if (it.isJsonPrimitive && it.asJsonPrimitive.isBoolean) {
            Ok(it.asBoolean)
        } else {
            Err("Expecting a Boolean but instead got: $it")
        }
    }

    val int: Decoder<Int> = {
        if (it.isJsonPrimitive && it.asJsonPrimitive.isNumber && "$it".isInt) {
            Ok(it.asInt)
        } else {
            Err("Expecting a Int but instead got: $it")
        }
    }

    val float: Decoder<Float> = {
        if (it.isJsonPrimitive && it.asJsonPrimitive.isNumber && !"$it".isInt && "$it".isFloat) {
            Ok(it.asFloat)
        } else {
            Err("Expecting a Float but instead got: $it")
        }
    }

    fun <T> nul(): Decoder<T?> {
        return {
            if (it.isJsonNull) {
                Ok(null as T?)
            } else {
                Err("Expecting a Null but instead got: $it")
            }
        }
    }

    fun <T> nullable(decoder: Decoder<T>): Decoder<T?> {
        return {
            if (it.isJsonNull) {
                Ok(null)
            } else {
                decoder(it).map { it }
            }
        }
    }

    fun <T> field(name: String, decoder: Decoder<T>): Decoder<T> {
        return {
            if (it.isJsonObject) {
                val obj = it.asJsonObject
                if (obj.has(name)) {
                    decoder(it.asJsonObject[name])
                } else {
                    Err("Expecting an object with a field named `$name` but instead got: {\"y\":4}")
                }
            } else {
                Err("Expecting an object but instead got: $it")
            }
        }
    }
}

private val String.isFloat: Boolean
    get() {
        try {
            java.lang.Float.parseFloat(this)
            return true
        } catch (e: NumberFormatException) {
            return false
        }
    }

private val String.isInt: Boolean
    get() {
        try {
            Integer.parseInt(this)
            return true
        } catch (e: NumberFormatException) {
            return false
        }
    }

fun <T> decodeString(decoder: Decoder<T>, json: String): Result<String, T> {
    return decoder(JsonParser().parse(json))
}
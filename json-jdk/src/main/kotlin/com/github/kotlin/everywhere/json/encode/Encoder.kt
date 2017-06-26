package com.github.kotlin.everywhere.json.encode

import com.google.gson.*

typealias Value = JsonElement

object Encoders {
    val string: (String) -> Value = ::JsonPrimitive
    val int: (Int) -> Value = ::JsonPrimitive
    val float: (Float) -> Value = ::JsonPrimitive
    val bool: (Boolean) -> Value = ::JsonPrimitive


    fun object_(vararg fields: Pair<String, Value>): Value {
        return fields.fold(JsonObject()) { obj, (name, value) -> obj.add(name, value); obj }
    }

    fun array(values: Collection<Value>): Value {
        return values.fold(JsonArray()) { arr, value -> arr.add(value); arr }
    }
}

private val gson = GsonBuilder().create()!!

fun encode(value: Value): String {
    return gson.toJson(value)
}
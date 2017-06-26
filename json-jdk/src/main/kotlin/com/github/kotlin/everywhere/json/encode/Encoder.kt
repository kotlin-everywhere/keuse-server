package com.github.kotlin.everywhere.json.encode

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive

typealias Value = JsonElement

object Encoders {
    val string: (String) -> Value = ::JsonPrimitive
    val int: (Int) -> Value = ::JsonPrimitive
    val float: (Float) -> Value = ::JsonPrimitive
    val bool: (Boolean) -> Value = ::JsonPrimitive
}

private val gson = GsonBuilder().create()!!

fun encode(value: Value): String {
    return gson.toJson(value)
}
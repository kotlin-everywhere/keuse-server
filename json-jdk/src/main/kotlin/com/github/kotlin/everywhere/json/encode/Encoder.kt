package com.github.kotlin.everywhere.json.encode

import com.google.gson.*

typealias Value = JsonElement

object Encoders {
    fun string(str: String): Value {
        return JsonPrimitive(str)
    }

    fun int(i: Int): Value {
        return JsonPrimitive(i)
    }

    fun float(f: Float): Value {
        return JsonPrimitive(f)
    }

    fun bool(b: Boolean): Value {
        return JsonPrimitive(b)
    }

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
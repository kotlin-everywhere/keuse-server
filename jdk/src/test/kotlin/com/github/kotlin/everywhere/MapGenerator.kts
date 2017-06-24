package com.github.kotlin.everywhere

import java.io.File

fun renderTypes(count: Int): String {
    return (1..count).map { "T$it" }.joinToString()
}

fun renderBody(i: Int, count: Int): String {
    if (i == count) {
        return "decoder$i(element).map { t$i -> mapper(${renderTypes(count).toLowerCase()})}"
    }
    return listOf(
            "decoder$i(element).andThen { t$i -> ",
            renderBody(i + 1, count),
            "}"
    )
            .joinToString("\n")
}

fun render(count: Int): List<String> {
    val decoders = (1..count).map { "decoder$it: Decoder<T$it>" }.joinToString()
    val header = "fun <${renderTypes(count)},U> map($decoders, mapper: (${renderTypes(count)}) -> U): Decoder<U> {"
    val body = renderBody(1, count)
    return listOf(header, "return { element -> ", body, "}", "}")
}

val headers = listOf(
        "package com.github.kotlin.everywhere.json.decode",
        "import com.google.gson.JsonElement"
)
val func = (1..22).flatMap { render(it) }
val source = (headers + func).joinToString("\n")

File("jdk/src/main/kotlin/com/github/kotlin/everywhere/json/decode/map.kt").writeText(source)

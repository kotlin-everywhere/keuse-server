package com.github.kotlin.everywhere.json.decode

sealed class Result<E, O> {
    fun <O2> map(mapper: (O) -> O2): Result<E, O2> {
        return when (this) {
            is Ok -> Ok(mapper(this.data))
            is Err -> Err(this.error)
        }
    }

    fun <O2> andThen(mapper: (O) -> Result<E, O2>): Result<E, O2> {
        return when (this) {
            is Ok -> mapper(this.data)
            is Err -> Err(this.error)
        }
    }
}


data class Ok<E, O>(val data: O) : Result<E, O>() {
    companion object {
        fun <O> of(data: O): Ok<*, O> {
            return Ok<Any, O>(data)
        }
    }
}

data class Err<E, O>(val error: E) : Result<E, O>() {
    companion object {
        fun <E> of(error: E): Err<E, *> {
            return Err<E, Any>(error)
        }
    }
}

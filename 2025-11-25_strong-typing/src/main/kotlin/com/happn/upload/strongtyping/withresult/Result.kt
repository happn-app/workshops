package com.happn.upload.strongtyping.withresult

import com.happn.upload.strongtyping.withresult.error.DomainError

sealed interface Result<out Data, out E : DomainError> {
    data class Success<out Data>(val value: Data) : Result<Data, Nothing>
    data class Failure<out E : DomainError>(val error: E) : Result<Nothing, E>
}
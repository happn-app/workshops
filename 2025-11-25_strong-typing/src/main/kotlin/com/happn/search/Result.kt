package com.happn.search

import com.happn.search.error.SearchServiceError

sealed interface Result<out Data, out E : SearchServiceError> {
    data class Success<out Data>(val value: Data) : Result<Data, Nothing>
    data class Failure<out E : SearchServiceError>(val error: E) : Result<Nothing, E>
}
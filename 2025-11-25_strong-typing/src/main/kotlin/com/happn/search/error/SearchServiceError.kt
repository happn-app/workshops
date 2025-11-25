package com.happn.search.error

import javax.annotation.processing.Messager

sealed interface SearchServiceError {
    class Unknown : SearchServiceError
}

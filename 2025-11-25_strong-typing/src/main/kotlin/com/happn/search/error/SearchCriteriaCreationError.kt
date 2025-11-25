package com.happn.search.error

sealed interface SearchCriteriaCreationError : SearchServiceError {
    class Unknown : SearchCriteriaCreationError
    class InvalidParameters : SearchCriteriaCreationError
}
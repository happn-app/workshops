package com.happn.search.error

sealed interface ElasticSearchError : SearchServiceError {
    class Unknown(val message: String?) : ElasticSearchError
    object NoProfilesFound : ElasticSearchError
    object EmptyWithParsingErrors : ElasticSearchError
}

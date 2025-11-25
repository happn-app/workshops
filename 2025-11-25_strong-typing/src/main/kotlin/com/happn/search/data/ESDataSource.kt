package com.happn.search.data

interface ESDataSource {
    fun search(query: ESQuery): ESResponse
}

class ESQuery(val parameters: Map<String, Any>)

class ESResponse(val documents: List<ESDocument>)

typealias ESDocument = Map<String, String>

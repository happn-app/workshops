package com.happn.search

import com.happn.search.criteria.Hobby
import com.happn.search.criteria.ProfileSearchCriteria
import com.happn.search.data.ESDataSource
import com.happn.search.data.ESQuery
import com.happn.search.data.ESResponse
import com.happn.search.error.ElasticSearchError
import com.happn.search.error.SearchCriteriaCreationError
import com.happn.search.error.SearchServiceError
import kotlin.test.Test

class ProfileSearchServiceTest {

    class DummyESDataSource : ESDataSource {
        override fun search(query: ESQuery): ESResponse {
            val profile1 = mapOf(
                "id" to "1",
                "name" to "John Doe",
                "hobbies" to "Coffee, Beer",
                "age" to "30",
                "photoUrl" to "photo_url",
            )
            val profile2 = mapOf(
                "id" to "2",
                "name" to "Jane Doe",
                "hobbies" to "Japan, Healthy",
                "age" to "30",
                "photoUrl" to "photo_url",
            )
            val profiles = listOf(profile1, profile2)
            return ESResponse(profiles)
        }
    }

    val esDataSource = DummyESDataSource()

    // TODO invalid document response
    // TODO search throws
    // TODO Empty with parsing errors
    // TODO Empty result

    @Test
    fun testSearch() {
        val criteria = ProfileSearchCriteria.HobbyBased(Hobby.COFFEE, 10)
        val service = ProfileSearchService(esDataSource)
        val result = service.search(criteria)
        when (result) {
            is Result.Failure -> handleFailure(result.error)
            is Result.Success -> {
                // I don't care about success ¯\_(ツ)_/¯
            }
        }
    }

    private fun handleFailure(error: SearchServiceError) {
        when (error) {
            is SearchServiceError.Unknown -> TODO()
            is ElasticSearchError.Unknown -> TODO()
            is ElasticSearchError.EmptyWithParsingErrors -> TODO()
            is ElasticSearchError.NoProfilesFound -> TODO()
            is SearchCriteriaCreationError.Unknown -> TODO()
            is SearchCriteriaCreationError.InvalidParameters -> TODO()
        }
    }
}
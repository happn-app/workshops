package com.happn.search

import com.happn.search.criteria.ProfileSearchCriteria
import com.happn.search.data.ESDataSource
import com.happn.search.data.ESDocument
import com.happn.search.data.ESQueryFactory
import com.happn.search.data.ESResponse
import com.happn.search.error.ElasticSearchError
import com.happn.search.error.SearchServiceError

class ProfileSearchService(
    val esDataSource: ESDataSource,
) {
    fun search(criteria: ProfileSearchCriteria): Result<List<Profile>, SearchServiceError> {
        return try {
            val esQuery = ESQueryFactory.create(criteria)
            val esResponse = esDataSource.search(esQuery)
            esResponse.toProfiles()
        } catch (e: Exception) {
            Result.Failure(ElasticSearchError.Unknown(e.message))
        }
    }

    private fun ESResponse.toProfiles(): Result<List<Profile>, ElasticSearchError> {
        if (documents.isEmpty()) {
            return Result.Failure(ElasticSearchError.NoProfilesFound)
        }

        val profiles = documents.mapNotNull { it.toProfile() }

        if (profiles.isEmpty()) {
            return Result.Failure(ElasticSearchError.EmptyWithParsingErrors)
        }

        return Result.Success(profiles)
    }

    private fun ESDocument.toProfile(): Profile? {
        // TODO Track parsing failures count and reasons
        val id = this["id"]?.toIntOrNull() ?: return null
        val name = this["name"] ?: return null
        val photoUrl = this["photoUrl"] ?: return null

        return Profile(id, name, photoUrl)
    }
}


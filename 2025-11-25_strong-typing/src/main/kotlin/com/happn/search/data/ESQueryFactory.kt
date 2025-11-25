package com.happn.search.data

import com.happn.search.criteria.ProfileSearchCriteria

object ESQueryFactory {

    // No Result handling here because input is compliant by typing
    fun create(criteria: ProfileSearchCriteria): ESQuery {
        val parameters = when (criteria) {
            is ProfileSearchCriteria.Timeline -> criteria.toESQuery()
            is ProfileSearchCriteria.HobbyBased -> criteria.toESQuery()
        }

        return ESQuery(parameters)
    }

    private fun ProfileSearchCriteria.Timeline.toESQuery(): Map<String, Any> {
        return mapOf(
            "distanceKm" to distanceKm.toString(),
        )
    }

    private fun ProfileSearchCriteria.HobbyBased.toESQuery(): Map<String, Any> {
        return mapOf(
            "distanceKm" to distanceKm.toString(),
            "hobby" to hobby.name,
        )
    }
}
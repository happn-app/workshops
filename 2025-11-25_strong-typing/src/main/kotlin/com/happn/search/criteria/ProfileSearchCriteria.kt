package com.happn.search.criteria

sealed class ProfileSearchCriteria(
    val distanceKm: Long,
) {
    class Timeline(distanceKm: Long) : ProfileSearchCriteria(
        distanceKm = distanceKm,
    )

    class HobbyBased(
        val hobby: Hobby,
        distanceKm: Long,
    ) : ProfileSearchCriteria(
        distanceKm = distanceKm,
    )
}

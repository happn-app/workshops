package com.happn.search

import com.happn.search.criteria.ProfileSearchCriteria
import com.happn.search.error.SearchCriteriaCreationError

interface SearchCriteriaFactory {
    fun create() : Result<ProfileSearchCriteria, SearchCriteriaCreationError>
}
package com.azlanjamal.search_domain.use_case

import com.azlanjamal.search_domain.model.Business
import com.azlanjamal.search_domain.repository.SearchRepository

class SearchBusiness(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(
        query: String,
        latitude: Double,
        longitude: Double
    ): Result<List<Business>> {
        if (query.isBlank()) {
            return Result.success(emptyList())
        }
        return repository.searchBusiness(
            query = query.trim(),
            latitude = latitude,
            longitude = longitude
        )
    }
}
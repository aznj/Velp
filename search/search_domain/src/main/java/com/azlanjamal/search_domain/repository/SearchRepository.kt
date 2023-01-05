package com.azlanjamal.search_domain.repository

import com.azlanjamal.search_domain.model.Business

interface SearchRepository {

    suspend fun searchBusiness(
        query: String,
        latitude: Double,
        longitude: Double
    ): Result<List<Business>>
}
package com.azlanjamal.search_data.repository

import com.azlanjamal.search_data.mapper.toBusiness
import com.azlanjamal.search_data.remote.SearchApi
import com.azlanjamal.search_domain.model.Business
import com.azlanjamal.search_domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val api: SearchApi
): SearchRepository {

    override suspend fun searchBusiness(
        query: String,
        latitude: Double,
        longitude: Double
    ): Result<List<Business>> {
        return try {
            val searchDto = api.searchBusiness(
                query = query,
                latitude = latitude,
                longitude = longitude
            )
            Result.success(
                searchDto.businesses.mapNotNull { it.toBusiness() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}
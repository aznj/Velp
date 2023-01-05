package com.azlanjamal.search_data.remote

import com.azlanjamal.search_data.remote.dto.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("businesses/search")
    suspend fun searchBusiness(
        @Query("term") query: String,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): SearchDto
    
}
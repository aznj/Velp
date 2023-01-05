package com.azlanjamal.detail_data.remote

import com.azlanjamal.detail_data.remote.dto.DetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApi {

    @GET("businesses/{businessId}")
    suspend fun getBusinessById(
        @Path("businessId") businessId: String
    ): DetailDto
}
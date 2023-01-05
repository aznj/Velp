package com.azlanjamal.location_domain.repository

import com.azlanjamal.location_domain.model.LatLng
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    suspend fun insertLatLng(latLng: LatLng)

    fun getLatestLatLng(): Flow<LatLng>
}
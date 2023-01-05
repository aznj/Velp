package com.azlanjamal.location_domain.use_case

import com.azlanjamal.location_domain.model.LatLng
import com.azlanjamal.location_domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow

class GetLatestLatLng(
    private val repository: LocationRepository
) {

    operator fun invoke(): Flow<LatLng> {
        return repository.getLatestLatLng()
    }
}
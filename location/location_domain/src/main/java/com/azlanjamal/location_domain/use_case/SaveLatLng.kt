package com.azlanjamal.location_domain.use_case

import com.azlanjamal.location_domain.model.LatLng
import com.azlanjamal.location_domain.repository.LocationRepository

class SaveLatLng(
    private val repository: LocationRepository
) {

    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ) {
        repository.insertLatLng(
            LatLng(
                latitude = latitude,
                longitude = longitude
            )
        )
    }
}
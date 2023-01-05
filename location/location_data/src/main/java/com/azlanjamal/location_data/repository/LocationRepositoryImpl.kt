package com.azlanjamal.location_data.repository

import com.azlanjamal.location_data.local.LatLngDao
import com.azlanjamal.location_data.mapper.toLatLng
import com.azlanjamal.location_data.mapper.toLatLngEntity
import com.azlanjamal.location_domain.model.LatLng
import com.azlanjamal.location_domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocationRepositoryImpl(
    private val dao: LatLngDao
): LocationRepository {

    override suspend fun insertLatLng(latLng: LatLng) {
        dao.insertLatLng(latLng.toLatLngEntity())
    }

    override fun getLatestLatLng(): Flow<LatLng> {
        return dao.getLatestLatLng().map { entities ->
            entities.toLatLng()
        }
    }
}
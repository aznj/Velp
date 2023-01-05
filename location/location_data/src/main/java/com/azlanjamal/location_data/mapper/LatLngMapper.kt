package com.azlanjamal.location_data.mapper

import com.azlanjamal.location_data.local.entity.LatLngEntity
import com.azlanjamal.location_domain.model.LatLng

fun LatLngEntity.toLatLng(): LatLng {
    return LatLng(
        latitude = latitude,
        longitude = longitude,
    )
}

fun LatLng.toLatLngEntity(): LatLngEntity {
    return LatLngEntity(
        latitude = latitude,
        longitude = longitude
    )
}
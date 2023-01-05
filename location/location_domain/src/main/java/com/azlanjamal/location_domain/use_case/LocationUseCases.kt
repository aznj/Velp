package com.azlanjamal.location_domain.use_case

data class LocationUseCases(
    val saveLatLng: SaveLatLng,
    val getLatestLatLng: GetLatestLatLng,
)
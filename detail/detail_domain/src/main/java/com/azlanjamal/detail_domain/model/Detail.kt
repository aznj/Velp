package com.azlanjamal.detail_domain.model

data class Detail(
    val name: String,
    val imageUrl: String,
    val coordinates: Coordinates,
    val isClaimed: Boolean,
    val phone: String,
    val location: Location,
    val photos: List<String>,
)

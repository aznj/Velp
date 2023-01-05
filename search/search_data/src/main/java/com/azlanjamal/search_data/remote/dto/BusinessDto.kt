package com.azlanjamal.search_data.remote.dto

data class BusinessDto(
    val categories: List<Category>,
    val coordinates: Coordinates,
    val id: String,
    val image_url: String,
    val location: Location,
    val name: String,
    val phone: String,
    val price: String,
    val rating: Double,
    val review_count: Int,
    val url: String
)
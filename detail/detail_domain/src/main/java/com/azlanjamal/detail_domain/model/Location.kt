package com.azlanjamal.detail_domain.model

data class Location(
    val address1: String,
    val address2: String,
    val address3: String,
    val city: String,
    val country: String,
    val crossStreets: String,
    val displayAddress: List<String>,
    val state: String,
    val zipCode: String
)

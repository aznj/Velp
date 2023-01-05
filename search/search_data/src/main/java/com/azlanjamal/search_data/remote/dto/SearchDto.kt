package com.azlanjamal.search_data.remote.dto

data class SearchDto(
    val total: Int,
    val businesses: List<BusinessDto>
)
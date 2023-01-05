package com.azlanjamal.search_presentation

import com.azlanjamal.search_domain.model.Business

data class SearchState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val businesses: List<Business> = emptyList(),
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
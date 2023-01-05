package com.azlanjamal.search_presentation

import com.azlanjamal.search_domain.model.Business

sealed class SearchEvent {
    data class OnQueryChange(val query: String) : SearchEvent()
    object OnSearch : SearchEvent()
    data class OnBusinessClick(
        val business: Business
    ) : SearchEvent()
    data class OnSearchFocusChange(val isFocused: Boolean): SearchEvent()
}
package com.azlanjamal.detail_presentation

import com.azlanjamal.detail_domain.model.Detail

data class DetailState(
    val isLoading: Boolean = false,
    val detail: Detail? = null,
    val error: String = ""
)
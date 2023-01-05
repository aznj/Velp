package com.azlanjamal.detail_data.remote.dto

data class Open(
    val day: Int,
    val end: String,
    val is_overnight: Boolean,
    val start: String
)
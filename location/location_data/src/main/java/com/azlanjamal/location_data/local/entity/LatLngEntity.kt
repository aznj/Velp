package com.azlanjamal.location_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LatLngEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val latitude: Double,
    val longitude: Double,
)

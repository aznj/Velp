package com.azlanjamal.velp.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.azlanjamal.location_data.local.LatLngDao
import com.azlanjamal.location_data.local.entity.LatLngEntity

@Database(
    entities = [LatLngEntity::class],
    version = 1
)
abstract class VelpDatabase: RoomDatabase() {

    abstract val dao: LatLngDao
}
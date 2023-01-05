package com.azlanjamal.location_data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.azlanjamal.location_data.local.entity.LatLngEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LatLngDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLatLng(latLngEntity: LatLngEntity)

    @Query(
        """
            SELECT * 
            FROM latlngentity
            ORDER BY id DESC LIMIT 1
        """
    )
    fun getLatestLatLng(): Flow<LatLngEntity>
}
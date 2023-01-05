package com.azlanjamal.velp.di

import android.app.Application
import androidx.room.Room
import com.azlanjamal.location_data.repository.LocationRepositoryImpl
import com.azlanjamal.location_domain.repository.LocationRepository
import com.azlanjamal.velp.local.VelpDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideVelpDatabase(app: Application): VelpDatabase {
        return Room.databaseBuilder(
            app,
            VelpDatabase::class.java,
            "velp_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocationRepository(
        db: VelpDatabase
    ): LocationRepository {
        return LocationRepositoryImpl(
            dao = db.dao,
        )
    }
}
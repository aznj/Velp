package com.azlanjamal.location_domain.di

import com.azlanjamal.location_domain.repository.LocationRepository
import com.azlanjamal.location_domain.use_case.GetLatestLatLng
import com.azlanjamal.location_domain.use_case.LocationUseCases
import com.azlanjamal.location_domain.use_case.SaveLatLng
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationDomainModule {

    @Provides
    @Singleton
    fun provideLocationUseCases(
        repository: LocationRepository,
    ): LocationUseCases {
        return LocationUseCases(
            saveLatLng = SaveLatLng(repository),
            getLatestLatLng = GetLatestLatLng(repository)
        )
    }
}
package com.azlanjamal.detail_domain.di

import com.azlanjamal.detail_domain.repository.DetailRepository
import com.azlanjamal.detail_domain.use_case.DetailUseCases
import com.azlanjamal.detail_domain.use_case.GetBusinessById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DetailDomainModule {

    @ViewModelScoped
    @Provides
    fun provideDetailUseCases(
        repository: DetailRepository
    ): DetailUseCases {
        return DetailUseCases(
            getBusinessById = GetBusinessById(repository)
        )
    }
}
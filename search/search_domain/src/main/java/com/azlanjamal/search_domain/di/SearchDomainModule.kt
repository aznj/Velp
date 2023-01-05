package com.azlanjamal.search_domain.di

import com.azlanjamal.search_domain.repository.SearchRepository
import com.azlanjamal.search_domain.use_case.SearchBusiness
import com.azlanjamal.search_domain.use_case.SearchUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SearchDomainModule {

    @ViewModelScoped
    @Provides
    fun provideSearchUseCases(
        repository: SearchRepository
    ): SearchUseCases {
        return SearchUseCases(
            searchBusiness = SearchBusiness(repository)
        )
    }
}
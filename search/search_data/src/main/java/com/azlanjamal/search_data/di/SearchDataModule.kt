package com.azlanjamal.search_data.di

import com.azlanjamal.core_data.common.Constant
import com.azlanjamal.search_data.remote.SearchApi
import com.azlanjamal.search_data.repository.SearchRepositoryImpl
import com.azlanjamal.search_domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchDataModule {

    @Provides
    @Singleton
    fun provideSearchApi(client: OkHttpClient): SearchApi {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSearchRepository(
        api: SearchApi
    ): SearchRepository {
        return SearchRepositoryImpl(
            api = api
        )
    }
}
package com.azlanjamal.detail_data.di

import com.azlanjamal.core_data.common.Constant
import com.azlanjamal.detail_data.remote.DetailApi
import com.azlanjamal.detail_data.repository.DetailRepositoryImpl
import com.azlanjamal.detail_domain.repository.DetailRepository
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
object DetailDataModule {

    @Provides
    @Singleton
    fun provideDetailApi(client: OkHttpClient): DetailApi {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideDetailRepository(
        api: DetailApi
    ): DetailRepository {
        return DetailRepositoryImpl(
            api = api
        )
    }
}
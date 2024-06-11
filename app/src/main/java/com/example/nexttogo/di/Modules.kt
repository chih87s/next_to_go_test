package com.example.nexttogo.di

import com.example.nexttogo.data.repository.FetchDataRepository
import com.example.nexttogo.data.repository.FetchDataRepositoryImpl
import com.example.nexttogo.data.remote.ApiServiceInterface
import com.example.nexttogo.data.remote.WebServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Modules {
    @Provides
    @Singleton
    fun provideWebServiceProvider(): ApiServiceInterface {
        return WebServiceProvider().provideApi()
    }

    @Provides
    @Singleton
    fun provideFetchDataRepository(api: ApiServiceInterface): FetchDataRepository {
        return FetchDataRepositoryImpl(api)
    }
}
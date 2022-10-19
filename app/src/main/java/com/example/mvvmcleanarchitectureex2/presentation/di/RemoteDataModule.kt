package com.example.mvvmcleanarchitectureex2.presentation.di

import com.example.mvvmcleanarchitectureex2.data.api.NewsAPIService
import com.example.mvvmcleanarchitectureex2.data.repository.dataSource.NewsRemoteDataSource
import com.example.mvvmcleanarchitectureex2.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsAPIService: NewsAPIService):NewsRemoteDataSource{
        return  NewsRemoteDataSourceImpl(newsAPIService)
    }
}
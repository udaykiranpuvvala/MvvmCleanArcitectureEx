package com.example.mvvmcleanarchitectureex2.presentation.di

import com.example.mvvmcleanarchitectureex2.domain.repository.NewsRepository
import com.example.mvvmcleanarchitectureex2.domain.usecase.GetNewsHeadlinesUseCase
import com.example.mvvmcleanarchitectureex2.domain.usecase.GetSearchedNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideNewsHeadLinesUseCase(newsRepository: NewsRepository):GetNewsHeadlinesUseCase{
        return GetNewsHeadlinesUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSearchedUseCase(newsRepository: NewsRepository):GetSearchedNewsUseCase{
        return GetSearchedNewsUseCase(newsRepository)
    }
}
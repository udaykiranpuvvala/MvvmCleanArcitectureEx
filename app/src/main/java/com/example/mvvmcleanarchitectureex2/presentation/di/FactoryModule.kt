package com.example.mvvmcleanarchitectureex2.presentation.di

import android.app.Application
import com.example.mvvmcleanarchitectureex2.domain.usecase.GetNewsHeadlinesUseCase
import com.example.mvvmcleanarchitectureex2.domain.usecase.GetSearchedNewsUseCase
import com.example.mvvmcleanarchitectureex2.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(application, getNewsHeadlinesUseCase,getSearchedNewsUseCase)
    }
}
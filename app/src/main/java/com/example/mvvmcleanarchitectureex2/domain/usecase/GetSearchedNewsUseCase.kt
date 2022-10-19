package com.example.mvvmcleanarchitectureex2.domain.usecase

import com.example.mvvmcleanarchitectureex2.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
}
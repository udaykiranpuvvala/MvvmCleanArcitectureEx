package com.example.mvvmcleanarchitectureex2.domain.usecase

import com.example.mvvmcleanarchitectureex2.data.util.Resource
import com.example.mvvmcleanarchitectureex2.domain.repository.NewsRepository
import com.unik.yunews.models.APIResponse

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country: String, searchQuery: String, page: Int): Resource<APIResponse> {
        return newsRepository.getSearchedNews(country, searchQuery, page)
    }
}
package com.example.mvvmcleanarchitectureex2.domain.usecase

import com.example.mvvmcleanarchitectureex2.domain.repository.NewsRepository
import com.example.mvvmcleanarchitectureex2.data.model.Article

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article)  = newsRepository.deleteNews(article)
}
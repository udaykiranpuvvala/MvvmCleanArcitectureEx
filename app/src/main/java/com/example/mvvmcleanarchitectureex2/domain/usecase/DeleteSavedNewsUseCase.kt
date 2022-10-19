package com.example.mvvmcleanarchitectureex2.domain.usecase

import com.example.mvvmcleanarchitectureex2.domain.repository.NewsRepository
import com.unik.yunews.models.Article

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article)  = newsRepository.deleteNews(article)
}
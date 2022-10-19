package com.example.mvvmcleanarchitectureex2.domain.usecase

import com.example.mvvmcleanarchitectureex2.data.util.Resource
import com.example.mvvmcleanarchitectureex2.domain.repository.NewsRepository
import com.unik.yunews.models.Article

class SaveNewsUseCase(private val newsRepository: NewsRepository)  {
    suspend fun execute(article: Article) = newsRepository.saveNews(article)
}
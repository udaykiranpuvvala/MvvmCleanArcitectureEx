package com.example.mvvmcleanarchitectureex2.domain.repository

import com.example.mvvmcleanarchitectureex2.data.util.Resource
import com.unik.yunews.models.APIResponse
import com.example.mvvmcleanarchitectureex2.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsHeadlines(country: String, page: Int) : Resource<APIResponse>

    suspend fun getSearchedNews(searchQuery:String) : Resource<APIResponse>

    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>

}
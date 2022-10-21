package com.example.mvvmcleanarchitectureex2.data.repository.dataSourceImpl

import com.example.mvvmcleanarchitectureex2.data.api.NewsAPIService
import com.example.mvvmcleanarchitectureex2.data.repository.dataSource.NewsRemoteDataSource
import com.unik.yunews.models.APIResponse
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService
):NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse> {
        return newsAPIService.getTopHeadLines(country, page)
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Response<APIResponse> {
        return  newsAPIService.getSearchedTopHeadLines(country, searchQuery, page)
    }


}
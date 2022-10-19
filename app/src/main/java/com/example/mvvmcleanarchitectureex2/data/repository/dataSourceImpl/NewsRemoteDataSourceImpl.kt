package com.example.mvvmcleanarchitectureex2.data.repository.dataSourceImpl

import com.example.mvvmcleanarchitectureex2.data.api.NewsAPIService
import com.example.mvvmcleanarchitectureex2.data.repository.dataSource.NewsRemoteDataSource
import com.unik.yunews.models.APIResponse
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService,
    private val country: String,
    private  val page :Int
):NewsRemoteDataSource {
    override suspend fun getTopHeadlines(): Response<APIResponse> {
        return newsAPIService.getTopHeadLines(country, page)
    }


}
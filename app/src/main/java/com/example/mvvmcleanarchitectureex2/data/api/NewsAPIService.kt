package com.example.mvvmcleanarchitectureex2.data.api

import com.example.mvvmcleanarchitectureex2.BuildConfig
import com.unik.yunews.models.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadLines(
        @Query("country")
        country:String,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apikey:String = BuildConfig.API_KEY
    ):Response<APIResponse>

    @GET("/v2/top-headlines")
    suspend fun getSearchedTopHeadLines(
        @Query("country")
        country:String,
        @Query("q")
        searchQuery: String,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apikey:String = BuildConfig.API_KEY
    ):Response<APIResponse>
}
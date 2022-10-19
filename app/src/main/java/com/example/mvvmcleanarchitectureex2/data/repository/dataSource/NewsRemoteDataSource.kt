package com.example.mvvmcleanarchitectureex2.data.repository.dataSource

import com.unik.yunews.models.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {

    suspend fun getTopHeadlines(country:String,page:Int): Response<APIResponse>

}
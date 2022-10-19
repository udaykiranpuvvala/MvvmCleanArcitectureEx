package com.example.mvvmcleanarchitectureex2.domain.repository

import com.unik.yunews.models.APIResponse

interface NewsRepository {

    suspend fun getNewsHeadlines() : APIResponse

}
package com.example.mvvmcleanarchitectureex2.data.repository

import com.example.mvvmcleanarchitectureex2.data.repository.dataSource.NewsRemoteDataSource
import com.example.mvvmcleanarchitectureex2.data.util.Resource
import com.example.mvvmcleanarchitectureex2.domain.repository.NewsRepository
import com.unik.yunews.models.APIResponse
import com.example.mvvmcleanarchitectureex2.data.model.Article
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


class NewsRepositoryImpl(
    private val  newsRemoteDataSource: NewsRemoteDataSource
):NewsRepository {
    override suspend fun getNewsHeadlines(country:String,page:Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines(country, page) )
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<APIResponse> {
        return  responseToResource(newsRemoteDataSource.getSearchedNews(country, searchQuery, page))
    }

    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse>{
        if(response.isSuccessful){
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return  Resource.Error(response.message())
    }

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}
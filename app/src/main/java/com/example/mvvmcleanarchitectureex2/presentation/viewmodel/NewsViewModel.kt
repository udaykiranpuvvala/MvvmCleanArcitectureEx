package com.example.mvvmcleanarchitectureex2.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcleanarchitectureex2.data.util.Resource
import com.example.mvvmcleanarchitectureex2.domain.usecase.GetNewsHeadlinesUseCase
import com.example.mvvmcleanarchitectureex2.domain.usecase.GetSearchedNewsUseCase
import com.unik.yunews.models.APIResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase
) :AndroidViewModel(app){
    val newsHeadLines :MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getNewsHeadLines(country: String, page:Int)= viewModelScope.launch(Dispatchers.IO) {
        newsHeadLines.postValue(Resource.Loading())
        try {
            val apiResult = getNewsHeadlinesUseCase.execute(country, page)
            newsHeadLines.postValue(apiResult)
        }catch (e: java.lang.Exception){
            newsHeadLines.postValue(Resource.Error(e.message.toString()))
        }
    }
    // getSearchedNewsUseCase

    val searchedNews: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun searchNews(country: String,searchQuery: String,page: Int)=viewModelScope.launch(Dispatchers.IO) {
        searchedNews.postValue(Resource.Loading())
        try {
            val response = getSearchedNewsUseCase.execute(country, searchQuery, page)
            searchedNews.postValue(response)
        }catch (e: java.lang.Exception){
            searchedNews.postValue(Resource.Error(e.message.toString()))
        }
    }

}
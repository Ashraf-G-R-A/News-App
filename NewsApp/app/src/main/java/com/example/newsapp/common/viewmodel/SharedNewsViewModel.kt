package com.example.newsapp.common.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.news.model.NewsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedNewsViewModel @Inject constructor() : ViewModel() {
    private val _selectedNews = MutableLiveData<NewsEntity?>()
    val selectedNews: LiveData<NewsEntity?> = _selectedNews

    fun getSelectedNews() = _selectedNews.value

    fun selectNews(news: NewsEntity?) {
        Log.e("SharedNewsViewModel", "selectNews: $news")
        _selectedNews.value = news
    }
}
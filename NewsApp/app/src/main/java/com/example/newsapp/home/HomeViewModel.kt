package com.example.newsapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.domain.news.model.NewsEntity
import com.example.domain.news.usecase.GetNewsByQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: GetNewsByQueryUseCase
) : ViewModel() {

    val news = newsUseCase.getNews(
        sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
    ).cachedIn(viewModelScope)

    fun addNews(news: NewsEntity) = viewModelScope.launch {
        newsUseCase.addNews(news)
    }

    fun deleteNews(news: NewsEntity) = viewModelScope.launch {
        newsUseCase.deleteNews(news)
    }

    val bookmarkedNews = newsUseCase.getAllNews(
    ).cachedIn(viewModelScope)


}

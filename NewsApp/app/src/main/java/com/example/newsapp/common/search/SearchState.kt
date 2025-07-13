package com.example.newsapp.common.search

import androidx.paging.PagingData
import com.example.domain.news.model.NewsEntity
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val query: String = "",
    val articles: Flow<PagingData<NewsEntity>>? = null

)

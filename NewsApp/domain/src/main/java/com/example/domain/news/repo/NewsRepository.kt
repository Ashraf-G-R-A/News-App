package com.example.domain.news.repo

import androidx.paging.PagingData
import com.example.domain.news.model.NewsEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNewsByQueryPaging(
        sources: List<String>,
    ): Flow<PagingData<NewsEntity>>

    fun searchNews(
        query: String,
        sources: List<String>,
    ): Flow<PagingData<NewsEntity>>

    fun getAllNews(): Flow<PagingData<NewsEntity>>
    suspend fun addNews(news: NewsEntity)
    suspend fun deleteNews(news: NewsEntity)
}

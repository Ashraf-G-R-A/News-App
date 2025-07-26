package com.example.domain.news.usecase

import com.example.domain.news.model.NewsEntity
import com.example.domain.news.repo.NewsRepository
import javax.inject.Inject

class GetNewsByQueryUseCase @Inject constructor(private val repository: NewsRepository) {
    fun getNews(sources: List<String>) =
        repository.getNewsByQueryPaging(sources)

    fun searchNews(query: String, sources: List<String>) =
        repository.searchNews(query, sources)

    fun getAllNews() = repository.getAllNews()
    suspend fun addNews(news: NewsEntity) = repository.addNews(news)
    suspend fun deleteNews(news: NewsEntity) = repository.deleteNews(news)
}

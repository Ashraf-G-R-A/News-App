package com.example.data.repo.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.local.db.dao.NewsDao
import com.example.data.remote.api.NewsApiService
import com.example.domain.news.model.NewsEntity
import com.example.domain.news.repo.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApiService,
    private val database: NewsDao,
    @Named("newsApiKey")
    private val apiKey: String
) : NewsRepository {

    override fun getNewsByQueryPaging(
        sources: List<String>,
    ): Flow<PagingData<NewsEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NewsPagingSource(
                    api = api,
                    sources = sources.joinToString(separator = ","),
                    apiKey = apiKey
                )
            }
        ).flow
    }

    override fun searchNews(query: String, sources: List<String>): Flow<PagingData<NewsEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchNewsSources(
                    api = api,
                    sources = sources.joinToString(separator = ","),
                    query = query,
                    apiKey = apiKey
                )
            }
        ).flow
    }

    override fun getAllNews(): Flow<PagingData<NewsEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                database.getAllNews()
            }
        ).flow
    }


    override suspend fun addNews(news: NewsEntity) {
        return database.addNews(news)
    }

    override suspend fun deleteNews(news: NewsEntity) {
        return database.deleteNews(news)
    }

}


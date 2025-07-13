package com.example.data.repo.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.remote.api.NewsApiService
import com.example.domain.news.model.NewsEntity
import com.example.domain.news.repo.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApiService,
    @Named("newsApiKey") private val apiKey: String
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

}


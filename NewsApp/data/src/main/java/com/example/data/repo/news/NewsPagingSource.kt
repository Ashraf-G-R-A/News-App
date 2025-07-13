package com.example.data.repo.news

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.mapper.toNewsEntity
import com.example.data.remote.api.NewsApiService
import com.example.domain.news.model.NewsEntity
import javax.inject.Named

class NewsPagingSource(
    private val api: NewsApiService,
    private val sources: String,
    @Named("newsApiKey") private val apiKey: String,
) : PagingSource<Int, NewsEntity>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsEntity> {
        val page = params.key ?: 1
        return try {
            val response = api.getEverything(
                page = page,
                apiKey = apiKey,
                sources = sources
            )

            if (response.articles.isNullOrEmpty()) {
                Log.e("NewsPagingSource", "Empty response received")
                return LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }

            val articles = response.articles
                .distinctBy { it.title }
                .map { it.toNewsEntity() }

            Log.e("NewsPagingSource", "Loaded ${articles[0]} articles. Page: $page")

            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (articles.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            Log.e("NewsPagingSource", "Error loading page $page: ${e.message}", e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val page = state.closestPageToPosition(anchorPosition)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }
}


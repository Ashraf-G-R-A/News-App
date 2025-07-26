package com.example.data.di

import com.example.data.local.db.dao.NewsDao
import com.example.data.remote.api.NewsApiService
import com.example.data.repo.news.NewsRepositoryImpl
import com.example.domain.news.repo.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        api: NewsApiService,
        @Named("newsApiKey") apiKey: String,
        newsDao: NewsDao
    ): NewsRepository =
        NewsRepositoryImpl(api, newsDao, apiKey)


}

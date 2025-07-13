package com.example.data.remote.api

import com.example.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything")
    suspend fun getEverything(
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String
    ): NewsResponseDto

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String
    ): NewsResponseDto
}

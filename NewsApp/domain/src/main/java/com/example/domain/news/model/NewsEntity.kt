package com.example.domain.news.model

data class NewsEntity(
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val author: String?,
    val sourceName: String
)

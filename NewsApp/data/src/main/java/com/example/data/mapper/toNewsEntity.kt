package com.example.data.mapper

import com.example.data.remote.dto.ArticleDto
import com.example.domain.news.model.NewsEntity

fun ArticleDto.toNewsEntity() = NewsEntity(
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt.substringBefore("T"),
    author = author,
    sourceName = source.name
)

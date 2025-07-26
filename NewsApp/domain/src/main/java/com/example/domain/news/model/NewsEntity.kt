package com.example.domain.news.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity(
    val title: String,
    val description: String?,
    val url: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val urlToImage: String?,
    val publishedAt: String,
    val author: String?,
    val sourceName: String
)

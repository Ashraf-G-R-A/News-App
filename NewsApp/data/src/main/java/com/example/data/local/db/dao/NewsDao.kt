package com.example.data.local.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.news.model.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNews(news: NewsEntity)

    @Delete
    suspend fun deleteNews(news: NewsEntity)


    @Query("SELECT * FROM NewsEntity")
    fun getAllNews(): PagingSource<Int, NewsEntity>
}
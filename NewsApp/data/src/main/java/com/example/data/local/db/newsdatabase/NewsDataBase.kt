package com.example.data.local.db.newsdatabase

import androidx.room.Database
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import com.example.data.local.db.dao.NewsDao
import com.example.domain.news.model.NewsEntity


@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDataBase : RoomDatabase() {

    abstract val newsDao: NewsDao

}
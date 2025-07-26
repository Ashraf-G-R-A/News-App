package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.db.newsdatabase.NewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {


    @Provides
    @Singleton
    fun provideNewsDataBase(
        @ApplicationContext context: Context,
        @Named("databaseName") databaseName: String
    ): NewsDataBase {
        return Room.databaseBuilder(
                context = context,
                klass = NewsDataBase::class.java,
                name = databaseName
            ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(newsDataBase: NewsDataBase) = newsDataBase.newsDao
}
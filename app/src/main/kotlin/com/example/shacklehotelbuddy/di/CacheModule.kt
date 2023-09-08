package com.example.shacklehotelbuddy.di

import android.content.Context
import androidx.room.Room
import com.example.shacklehotelbuddy.data.cache.AppCacheDatabase
import com.example.shacklehotelbuddy.data.cache.daos.SearchHistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Singleton
    @Provides
    fun provideCacheDatabase(@ApplicationContext context: Context): AppCacheDatabase =
        Room.databaseBuilder(context, AppCacheDatabase::class.java, "app_cache")
            .fallbackToDestructiveMigration().build()

    @Provides
    fun provideSearchHistoryDao(appCacheDatabase: AppCacheDatabase): SearchHistoryDao =
        appCacheDatabase.trendReposDao()
}
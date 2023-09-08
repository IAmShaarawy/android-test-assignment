package com.example.shacklehotelbuddy.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shacklehotelbuddy.data.cache.daos.SearchHistoryDao
import com.example.shacklehotelbuddy.data.cache.entities.SearchHistoryEntity

@Database(
    version = 2,
    exportSchema = false,
    entities = [SearchHistoryEntity::class]
)
abstract class AppCacheDatabase : RoomDatabase() {
    abstract fun trendReposDao(): SearchHistoryDao
}
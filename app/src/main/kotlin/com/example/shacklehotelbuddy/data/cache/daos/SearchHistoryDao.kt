package com.example.shacklehotelbuddy.data.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shacklehotelbuddy.data.cache.entities.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {
    @Insert
    suspend fun insertSearchEntity(item: SearchHistoryEntity):Long

    @Query("SELECT * FROM search_history ORDER BY id DESC LIMIT 1")
     fun getLatestSearchEntity(): Flow<SearchHistoryEntity?>
}
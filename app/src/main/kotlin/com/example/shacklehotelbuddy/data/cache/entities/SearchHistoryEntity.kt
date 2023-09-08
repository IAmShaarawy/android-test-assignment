package com.example.shacklehotelbuddy.data.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("search_history")
data class SearchHistoryEntity(
    @ColumnInfo("id") @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo("checkIn") val checkIn: String?,
    @ColumnInfo("checkOut") val checkOut: String?,
    @ColumnInfo("adultsCount") val adultsCount: Int,
    @ColumnInfo("childrenCount") val childrenCount: Int,
)
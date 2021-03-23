package com.hujiejeff.wanadnroid.module.base.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "read_history")
data class ReadHistory(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "contentId") val contentId: String,
    @ColumnInfo(name = "last_read_time") val lastReadTime: Long
)
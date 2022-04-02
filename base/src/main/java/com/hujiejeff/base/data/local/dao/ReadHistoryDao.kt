package com.hujiejeff.base.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hujiejeff.base.data.local.entity.ReadHistory

@Dao
interface ReadHistoryDao {
    @Query("SELECT * FROM read_history")
    suspend fun getAllHistories(): List<ReadHistory>

    @Delete
    suspend fun deleteHistory(readHistory: ReadHistory)

    @Insert
    suspend fun addHistory(readHistory: ReadHistory)
}
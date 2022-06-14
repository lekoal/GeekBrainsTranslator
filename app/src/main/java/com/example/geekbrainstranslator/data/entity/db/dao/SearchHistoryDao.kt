package com.example.geekbrainstranslator.data.entity.db.dao

import androidx.room.*
import com.example.geekbrainstranslator.data.entity.db.WordData

@Dao
interface SearchHistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun historyInsert(entity: WordData)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun historyInsertAll(entityList: List<WordData>)

    @Query("SELECT * FROM WordData")
    suspend fun historyGetAll(): List<WordData>

    @Query("SELECT * FROM WordData WHERE text LIKE :word")
    suspend fun historyGetWord(word: String): WordData

    @Update
    suspend fun historyUpdate(entity: WordData)

    @Delete
    suspend fun historyDelete(entity: WordData)

    @Delete
    suspend fun historyClear()
}
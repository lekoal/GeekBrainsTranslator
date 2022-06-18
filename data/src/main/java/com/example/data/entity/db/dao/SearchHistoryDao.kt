package com.example.data.entity.db.dao

import androidx.room.*
import com.example.data.entity.db.WordData

@Dao
interface SearchHistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun historyInsert(entity: WordData)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun historyInsertAll(entityList: List<WordData>)

    @Query("SELECT * FROM WordData")
    fun historyGetAll(): List<WordData>

    @Query("SELECT * FROM WordData WHERE text LIKE :arg0")
    fun historyGetWord(arg0: String): WordData

    @Update
    fun historyUpdate(entity: WordData)

    @Delete
    fun historyDelete(entity: WordData)

    @Query("DELETE FROM WordData")
    fun clearTable()
}
package com.example.geekbrainstranslator.data.entity.db.dao

import androidx.room.*
import com.example.geekbrainstranslator.data.entity.db.WordData

@Dao
interface FavoriteWordsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun favoriteInsert(entity: WordData)

    @Query("SELECT * FROM WordData")
    suspend fun favoriteGetAll(): List<WordData>

    @Query("SELECT * FROM WordData WHERE text LIKE :word")
    suspend fun favoriteGetWord(word: String): WordData

    @Update
    suspend fun favoriteUpdate(entity: WordData)

    @Delete
    suspend fun favoriteDeleteWord(entity: WordData)
}
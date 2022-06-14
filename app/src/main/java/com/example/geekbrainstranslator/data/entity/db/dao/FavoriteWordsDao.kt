package com.example.geekbrainstranslator.data.entity.db.dao

import androidx.room.*
import com.example.geekbrainstranslator.data.entity.db.WordData

@Dao
interface FavoriteWordsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun favoriteInsert(entity: WordData)

    @Query("SELECT * FROM WordData")
    fun favoriteGetAll(): List<WordData>

    @Query("SELECT * FROM WordData WHERE text LIKE :word")
    fun favoriteGetWord(word: String): WordData

    @Update
    fun favoriteUpdate(entity: WordData)

    @Delete
    fun favoriteDeleteWord(entity: WordData)
}
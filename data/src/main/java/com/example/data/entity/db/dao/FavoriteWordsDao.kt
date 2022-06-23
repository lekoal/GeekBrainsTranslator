package com.example.data.entity.db.dao

import androidx.room.*
import com.example.data.entity.db.WordData

@Dao
interface FavoriteWordsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun favoriteInsert(entity: WordData)

    @Query("SELECT * FROM WordData")
    fun favoriteGetAll(): List<WordData>

    @Query("SELECT * FROM WordData WHERE text LIKE :arg0")
    fun favoriteGetWord(arg0: String): WordData

    @Update
    fun favoriteUpdate(entity: WordData)

    @Delete
    fun favoriteDeleteWord(entity: WordData)
}
package com.example.geekbrainstranslator.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.geekbrainstranslator.data.entity.db.WordData
import com.example.geekbrainstranslator.data.entity.db.dao.FavoriteWordsDao

@Database(entities = [WordData::class], version = 1, exportSchema = true)
abstract class FavoriteWordsDataBase : RoomDatabase() {
    abstract fun favoriteWordsDao(): FavoriteWordsDao
}
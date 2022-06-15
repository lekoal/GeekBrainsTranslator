package com.example.geekbrainstranslator.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.geekbrainstranslator.data.converters.StringListConverter
import com.example.geekbrainstranslator.data.entity.db.WordData
import com.example.geekbrainstranslator.data.entity.db.dao.FavoriteWordsDao

@Database(entities = [WordData::class], version = 1, exportSchema = true)
@TypeConverters(StringListConverter::class)
abstract class FavoriteWordsDataBase : RoomDatabase() {
    abstract fun favoriteWordsDao(): FavoriteWordsDao
}
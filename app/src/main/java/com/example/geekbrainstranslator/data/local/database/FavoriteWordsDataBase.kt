package com.example.geekbrainstranslator.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.entity.db.WordData
import com.example.data.entity.db.dao.FavoriteWordsDao
import com.example.geekbrainstranslator.data.converters.StringListConverter

@Database(entities = [WordData::class], version = 1, exportSchema = true)
@TypeConverters(StringListConverter::class)
abstract class FavoriteWordsDataBase : RoomDatabase() {
    abstract fun favoriteWordsDao(): FavoriteWordsDao
}
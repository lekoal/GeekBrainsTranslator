package com.example.geekbrainstranslator.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.geekbrainstranslator.data.converters.StringListConverter
import com.example.geekbrainstranslator.data.entity.db.WordData
import com.example.geekbrainstranslator.data.entity.db.dao.SearchHistoryDao

@Database(entities = [WordData::class], version = 1, exportSchema = true)
@TypeConverters(StringListConverter::class)
abstract class SearchHistoryDataBase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao
}
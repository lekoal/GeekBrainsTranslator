package com.example.geekbrainstranslator.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.entity.db.WordData
import com.example.data.entity.db.dao.SearchHistoryDao
import com.example.geekbrainstranslator.data.converters.StringListConverter

@Database(entities = [WordData::class], version = 1, exportSchema = true)
@TypeConverters(StringListConverter::class)
abstract class SearchHistoryDataBase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao
}
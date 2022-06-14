package com.example.geekbrainstranslator.data.entity.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index("id", unique = true)])
data class WordData(
    @field: PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "index")
    val index: Int = 0,
    @field: ColumnInfo(name = "id")
    val id: Int,
    @field: ColumnInfo(name = "text")
    val text:String,
    @field: ColumnInfo(name = "imageUrl")
    val imageUrl: String,
    @field: ColumnInfo(name = "transcription")
    val transcription: String,
    @field: ColumnInfo(name = "translation")
    val translation: String,
    @field: ColumnInfo(name = "partOfSpeechCode")
    val partOfSpeechCode: String
)

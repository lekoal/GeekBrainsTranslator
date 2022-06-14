package com.example.geekbrainstranslator.data.entity.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordData(
    @field: PrimaryKey
    val id: Int,
    val imageUrl: String,
    val transcription: String,
    val translation: String,
    val partOfSpeechCode: String
)

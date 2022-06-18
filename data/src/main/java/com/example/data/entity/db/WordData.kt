package com.example.data.entity.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index("id", unique = true)])
data class WordData(
    @field: PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = INDEX_NAME)
    val index: Int = 0,
    @field: ColumnInfo(name = ID_NAME)
    val id: Int?,
    @field: ColumnInfo(name = TEXT_NAME)
    val text:String?,
    @field: ColumnInfo(name = IMAGE_URL_NAME)
    val imageUrl: List<String?>,
    @field: ColumnInfo(name = TRANSCRIPTION_NAME)
    val transcription: List<String?>,
    @field: ColumnInfo(name = TRANSLATION_NAME)
    val translation: List<String?>,
    @field: ColumnInfo(name = PART_OF_SPEECH_NAME)
    val partOfSpeechCode: List<String?>
) {
    companion object {
        private const val INDEX_NAME = "index"
        private const val ID_NAME = "id"
        private const val TEXT_NAME = "text"
        private const val IMAGE_URL_NAME = "imageUrl"
        private const val TRANSCRIPTION_NAME = "transcription"
        private const val TRANSLATION_NAME = "translation"
        private const val PART_OF_SPEECH_NAME = "partOfSpeechCode"
    }
}
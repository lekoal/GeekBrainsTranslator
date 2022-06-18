package com.example.domain

import com.example.data.entity.db.WordData

interface SearchHistoryUsecase {
    suspend fun getDataFromDB(): List<WordData>
    suspend fun deleteDataFromDB(data: WordData)
    suspend fun getDataItemFromDB(text: String): WordData
    suspend fun clearDB()
}
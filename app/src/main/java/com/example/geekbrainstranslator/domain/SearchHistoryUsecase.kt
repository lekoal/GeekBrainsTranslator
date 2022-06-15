package com.example.geekbrainstranslator.domain

import com.example.geekbrainstranslator.data.entity.db.WordData
import com.example.geekbrainstranslator.data.entity.web.TranslateDTO

interface SearchHistoryUsecase {
    fun addDataToDB(dataList: List<TranslateDTO>)
    suspend fun getDataFromDB(): List<WordData>
    suspend fun deleteDataFromDB(data: WordData)
    suspend fun clearDB()
}
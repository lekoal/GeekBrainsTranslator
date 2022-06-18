package com.example.domain

import com.example.data.entity.db.WordData
import com.example.data.entity.web.TranslateDTO
import kotlinx.coroutines.Deferred

interface RepositoryUsecase {
    suspend fun receiveAsync(word: String): Deferred<List<TranslateDTO>>
    fun addDataToDB(data: TranslateDTO)
    suspend fun searchDataInDB(text: String): WordData?
}
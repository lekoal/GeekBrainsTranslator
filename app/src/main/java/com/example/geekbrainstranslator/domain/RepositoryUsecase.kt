package com.example.geekbrainstranslator.domain

import com.example.geekbrainstranslator.data.entity.web.TranslateDTO
import kotlinx.coroutines.Deferred

interface RepositoryUsecase {
    suspend fun receiveAsync(word: String): Deferred<List<TranslateDTO>>
    fun addDataToDB(data: TranslateDTO)
}
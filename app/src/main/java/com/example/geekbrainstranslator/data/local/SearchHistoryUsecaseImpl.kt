package com.example.geekbrainstranslator.data.local

import com.example.geekbrainstranslator.data.entity.db.WordData
import com.example.geekbrainstranslator.data.entity.db.dao.SearchHistoryDao
import com.example.geekbrainstranslator.data.entity.web.TranslateDTO
import com.example.geekbrainstranslator.domain.SearchHistoryUsecase
import kotlinx.coroutines.*

class SearchHistoryUsecaseImpl(private val historyDao: SearchHistoryDao) : SearchHistoryUsecase {

    private val wordData = mutableListOf<WordData>()
    private val imageList = mutableListOf<String?>()
    private val transcription = mutableListOf<String?>()
    private val translation = mutableListOf<String?>()
    private val partOfSpeech = mutableListOf<String?>()

    private val coroutineScope = CoroutineScope(
    Dispatchers.IO
    + SupervisorJob()
    )

    override fun addDataToDB(dataList: List<TranslateDTO>) {
        val job = coroutineScope.launch {
            wordData.clear()
            dataList.forEach { translateDTO ->
                translateDTO.meanings?.forEach { meaning ->
                    imageList.add(meaning.imageUrl)
                    transcription.add(meaning.transcription)
                    partOfSpeech.add(meaning.partOfSpeechCode)
                    translation.add(meaning.translation?.text)
                }
                wordData.add(
                    WordData(
                        id = translateDTO.id,
                        text = translateDTO.text,
                        imageUrl = imageList,
                        transcription = transcription,
                        translation = translation,
                        partOfSpeechCode = partOfSpeech
                    )
                )
            }
            historyDao.historyInsertAll(wordData)
        }
        if (job.isCompleted) coroutineScope.cancel()
    }

    override suspend fun getDataFromDB(): List<WordData> = wordData

    override suspend fun deleteDataFromDB(data: WordData) {
        historyDao.historyDelete(data)
    }

    override suspend fun clearDB() {
        historyDao.clearTable()
    }
}
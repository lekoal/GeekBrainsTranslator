package com.example.geekbrainstranslator.data.local

import com.example.data.entity.db.WordData
import com.example.data.entity.db.dao.SearchHistoryDao
import com.example.domain.SearchHistoryUsecase

class SearchHistoryUsecaseImpl(private val historyDao: SearchHistoryDao) : SearchHistoryUsecase {

    override suspend fun getDataFromDB(): List<WordData> {
        return historyDao.historyGetAll()
    }

    override suspend fun deleteDataFromDB(data: WordData) {
        historyDao.historyDelete(data)
    }

    override suspend fun getDataItemFromDB(text: String): WordData {
        return historyDao.historyGetWord(text)
    }

    override suspend fun clearDB() {
        historyDao.clearTable()
    }
}
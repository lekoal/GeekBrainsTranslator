package com.example.geekbrainstranslator.view.story.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.data.entity.db.WordData
import com.example.domain.SearchHistoryUsecase
import com.example.geekbrainstranslator.view.story.SearchStoryContract
import kotlinx.coroutines.*

class SearchHistoryViewModel(
    private val historyUsecase: SearchHistoryUsecase
) : SearchStoryContract.ViewModel() {
    override var storyList: MutableLiveData<List<WordData>> = MutableLiveData<List<WordData>>()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var deleteHistoryJob: Job? = null
    private var clearHistoryJob: Job? = null
    private var getHistoryJob: Job? = null

    override fun getHistory() {
        getHistoryJob = coroutineScope.launch {
            storyList.postValue(
                historyUsecase.getDataFromDB()
            )
        }
    }

    override fun deleteHistoryItem(item: WordData) {
        deleteHistoryJob = coroutineScope.launch {
            historyUsecase.deleteDataFromDB(item)
        }
    }

    override fun clearHistory() {
        clearHistoryJob = coroutineScope.launch {
            historyUsecase.clearDB()
        }
    }

    override fun restoreHistory() {

    }

    override fun onCleared() {
        coroutineScope.cancel()
        super.onCleared()
    }
}
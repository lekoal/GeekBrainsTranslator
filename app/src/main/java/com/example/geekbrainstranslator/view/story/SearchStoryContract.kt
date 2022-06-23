package com.example.geekbrainstranslator.view.story

import androidx.lifecycle.LiveData
import com.example.data.entity.db.WordData

interface SearchStoryContract {
    interface View {
        fun setData()
    }

    abstract class ViewModel : androidx.lifecycle.ViewModel() {
        abstract val storyList: LiveData<List<WordData>>
        abstract fun getHistory()
        abstract fun deleteHistoryItem(item: WordData)
        abstract fun clearHistory()
        abstract fun restoreHistory()
    }
}
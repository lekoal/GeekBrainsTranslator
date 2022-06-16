package com.example.geekbrainstranslator.view.description

import androidx.lifecycle.LiveData
import com.example.geekbrainstranslator.data.entity.db.WordData

interface DescriptionWordContract {
    interface View {
        fun setData()
    }

    abstract class ViewModel : androidx.lifecycle.ViewModel() {
        abstract val wordDetails: LiveData<WordData>
        abstract fun setWordDetails(item: WordData)
        abstract fun restoreWordDetails()
    }
}
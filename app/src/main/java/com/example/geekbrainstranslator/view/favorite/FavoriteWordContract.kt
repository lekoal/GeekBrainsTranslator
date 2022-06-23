package com.example.geekbrainstranslator.view.favorite

import androidx.lifecycle.LiveData
import com.example.data.entity.db.WordData

interface FavoriteWordContract {
    interface View {
        fun setData()
    }

    abstract class ViewModel : androidx.lifecycle.ViewModel() {
        abstract val favoriteList: LiveData<List<WordData>>
        abstract fun getFavorite()
        abstract fun deleteFavoriteItem(item: WordData)
        abstract fun restoreFavorite()
    }
}
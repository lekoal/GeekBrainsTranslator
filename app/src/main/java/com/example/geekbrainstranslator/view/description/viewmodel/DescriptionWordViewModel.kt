package com.example.geekbrainstranslator.view.description.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.geekbrainstranslator.data.entity.db.WordData
import com.example.geekbrainstranslator.view.description.DescriptionWordContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DescriptionWordViewModel : DescriptionWordContract.ViewModel() {
    override val wordDetails: MutableLiveData<WordData> = MutableLiveData<WordData>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun setWordDetails(item: WordData) {
        coroutineScope.launch {
            wordDetails.postValue(item)
        }
    }

    override fun restoreWordDetails() {

    }

    override fun onCleared() {
        coroutineScope.cancel()
        super.onCleared()
    }
}
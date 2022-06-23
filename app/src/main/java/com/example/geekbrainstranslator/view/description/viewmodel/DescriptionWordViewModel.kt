package com.example.geekbrainstranslator.view.description.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.data.entity.db.WordData
import com.example.domain.SearchHistoryUsecase
import com.example.geekbrainstranslator.view.description.DescriptionWordContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DescriptionWordViewModel(
    private val historyUsecase: SearchHistoryUsecase
) : DescriptionWordContract.ViewModel() {
    override val wordDetails: MutableLiveData<WordData> = MutableLiveData<WordData>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun setWordDetails(itemName: String) {
        coroutineScope.launch {
            val tempData = historyUsecase.getDataItemFromDB(itemName)
            wordDetails.postValue(tempData)
        }
    }

    override fun restoreWordDetails() {

    }

    override fun onCleared() {
        coroutineScope.cancel()
        super.onCleared()
    }
}
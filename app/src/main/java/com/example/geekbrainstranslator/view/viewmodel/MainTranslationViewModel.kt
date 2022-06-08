package com.example.geekbrainstranslator.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.geekbrainstranslator.data.entity.TranslateDTO
import com.example.geekbrainstranslator.domain.RepositoryUsecase
import com.example.geekbrainstranslator.view.MainTranslationContract
import kotlinx.coroutines.*

class MainTranslationViewModel
constructor(
    private val repoUsecase: RepositoryUsecase,
    private var handle: SavedStateHandle
) : MainTranslationContract.ViewModel() {

    override val result: MutableLiveData<List<TranslateDTO>> = MutableLiveData<List<TranslateDTO>>()

    override val inProgress: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    override val onError: MutableLiveData<Throwable> = MutableLiveData()

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    override fun onRestore() {
        setQuery()

        val filteredData: MutableLiveData<List<TranslateDTO>> =
            handle.getLiveData(HANDLE_KEY)

        filteredData.apply {
            this.value?.let { result.postValue(it) }
        }
    }

    override fun onSearch(word: String) {
        cancelJob()
        viewModelCoroutineScope.launch {
            inProgress.postValue(true)
            searchLong(word)
            inProgress.postValue(false)
        }
    }

    private suspend fun searchLong(word: String) = withContext(Dispatchers.IO) {
        result.postValue(
            repoUsecase
                .receiveAsync(word)
                .await()
        )
    }

    private fun setQuery() {
        result.apply {
            handle[HANDLE_KEY] = this.value
        }
    }

    private fun handleError(throwable: Throwable) {
        onError.postValue(throwable)
    }

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    companion object {
        const val HANDLE_KEY = "query"
    }

    override fun onCleared() {
        cancelJob()
        super.onCleared()
    }
}
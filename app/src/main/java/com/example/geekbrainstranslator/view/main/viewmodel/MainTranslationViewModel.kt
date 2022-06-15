package com.example.geekbrainstranslator.view.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.geekbrainstranslator.data.entity.web.TranslateDTO
import com.example.geekbrainstranslator.domain.RepositoryUsecase
import com.example.geekbrainstranslator.view.main.MainTranslationContract
import kotlinx.coroutines.*

class MainTranslationViewModel
constructor(
    private val repoUsecase: RepositoryUsecase,
    private var handle: SavedStateHandle
) : MainTranslationContract.ViewModel() {

    override val result: MutableLiveData<List<TranslateDTO>> = MutableLiveData<List<TranslateDTO>>()

    override val inProgress: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    override val onError: MutableLiveData<Throwable> = MutableLiveData()

    private var job: Job? = null

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.IO
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

    override fun sendDataToDB(data: List<TranslateDTO>) {
        viewModelCoroutineScope.launch {
            repoUsecase.addDataToDB(data[0])
        }
    }

    override fun onSearch(word: String) {
        if(job != null) {
            cancelJob()
        }
        job = viewModelCoroutineScope.launch {
            inProgress.postValue(true)
            result.postValue(
                repoUsecase
                    .receiveAsync(word)
                    .await()
            )
            inProgress.postValue(false)
        }
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
        job?.cancel()
    }

    companion object {
        const val HANDLE_KEY = "query"
    }

    override fun onCleared() {
        viewModelCoroutineScope.cancel()
        super.onCleared()
    }
}
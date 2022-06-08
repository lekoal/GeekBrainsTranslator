package com.example.geekbrainstranslator.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.geekbrainstranslator.data.entity.TranslateDTO
import com.example.geekbrainstranslator.domain.RepositoryUsecase
import com.example.geekbrainstranslator.view.MainTranslationContract
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class MainTranslationViewModel
@Inject
constructor(
    private val repoUsecase: RepositoryUsecase,
    private var handle: SavedStateHandle
) : MainTranslationContract.ViewModel() {
    private val myCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override val result: MutableLiveData<List<TranslateDTO>> = MutableLiveData<List<TranslateDTO>>()

    override val inProgress: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    override fun onRestore() {
        setQuery()

        val filteredData: MutableLiveData<List<TranslateDTO>> =
            handle.getLiveData(HANDLE_KEY)

        filteredData.apply {
            this.value?.let { result.postValue(it) }
        }
    }

    override fun onSearch(word: String) {
        inProgress.postValue(true)
        myCompositeDisposable.add(
            repoUsecase
                .receive(word)
                .subscribeBy {
                    inProgress.postValue(false)
                    result.postValue(it)
                }
        )
    }

    private fun setQuery() {
        result.apply {
            handle[HANDLE_KEY] = this.value
        }
    }

    companion object {
        const val HANDLE_KEY = "query"
    }
}
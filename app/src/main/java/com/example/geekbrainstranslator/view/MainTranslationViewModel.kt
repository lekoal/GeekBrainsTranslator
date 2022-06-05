package com.example.geekbrainstranslator.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geekbrainstranslator.data.entity.TranslateDTO
import com.example.geekbrainstranslator.domain.RepositoryUsecase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class MainTranslationViewModel(
    private val repoUsecase: RepositoryUsecase
) : MainTranslationContract.ViewModel, ViewModel() {
    private val myCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override val result = MutableLiveData<List<TranslateDTO>>()

    override val inProgress = MutableLiveData<Boolean>()

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
}
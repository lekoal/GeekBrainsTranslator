package com.example.geekbrainstranslator.view

import androidx.lifecycle.MutableLiveData
import com.example.geekbrainstranslator.data.RepoUsecaseImpl
import com.example.geekbrainstranslator.data.entity.TranslateDTO
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class MainTranslationViewModel : MainTranslationContract.ViewModel() {
    private val myCompositeDisposable: CompositeDisposable = CompositeDisposable()

    private val repoUsecase = RepoUsecaseImpl()

    override val result: MutableLiveData<List<TranslateDTO>> = MutableLiveData<List<TranslateDTO>>()

    override val inProgress: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

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
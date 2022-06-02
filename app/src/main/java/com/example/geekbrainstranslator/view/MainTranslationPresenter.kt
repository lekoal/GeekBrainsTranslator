package com.example.geekbrainstranslator.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.geekbrainstranslator.data.entity.TranslateDTO
import com.example.geekbrainstranslator.domain.RepositoryUsecase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class MainTranslationPresenter(
    private val repoUsecase: RepositoryUsecase
) : MainTranslationContract.Presenter {
    private var currentView: MainTranslationContract.View? = null
    private val myCompositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _result = MutableLiveData<List<TranslateDTO>>()
    val result: LiveData<List<TranslateDTO>> = _result

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> = _inProgress


    override fun onViewAttach(view: MainTranslationContract.View) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun onViewDetach(view: MainTranslationContract.View) {
        if (currentView == view) {
            currentView = null
        }
    }

    override fun onSearch(word: String) {
        _inProgress.postValue(true)
        myCompositeDisposable.add(
            repoUsecase
                .receive(word)
                .subscribeBy {
                    _inProgress.postValue(false)
                    _result.postValue(it)
                }
        )

    }
}
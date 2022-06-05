package com.example.geekbrainstranslator.view

import com.example.geekbrainstranslator.domain.RepositoryUsecase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainTranslationPresenter(
    private val repoUsecase: RepositoryUsecase
) : MainTranslationContract.Presenter {
    private var currentView: MainTranslationContract.View? = null
    private val myCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private val schedulerUI = AndroidSchedulers.mainThread()
    private val schedulerIO = Schedulers.io()

//    private val _result = MutableLiveData<List<TranslateDTO>>()
//    val result: LiveData<List<TranslateDTO>> = _result
//
//    private val _inProgress = MutableLiveData<Boolean>()
//    val inProgress: LiveData<Boolean> = _inProgress

//    override fun onSearch(word: String) {
//        _inProgress.postValue(true)
//        myCompositeDisposable.add(
//            repoUsecase
//                .receive(word)
//                .subscribeBy {
//                    _inProgress.postValue(false)
//                    _result.postValue(it)
//                }
//        )
//
//    }

    override fun viewAttach(view: MainTranslationContract.View) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun viewDetach(view: MainTranslationContract.View) {
        if (currentView == view) {
            currentView = null
        }
    }

    override fun getData(word: String) {
        currentView?.setProcessLoading(true)
        currentView?.setResultVisibility(false)
        myCompositeDisposable.add(
            repoUsecase
                .receive(word)
                .subscribeOn(schedulerIO)
                .observeOn(schedulerUI)
                .subscribeBy {
                    currentView?.setProcessLoading(false)
                    currentView?.setResultVisibility(true)
                    currentView?.setSearchSuccess(it)
                }
        )
    }
}
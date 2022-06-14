package com.example.geekbrainstranslator.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.geekbrainstranslator.data.entity.web.TranslateDTO

interface MainTranslationContract {
    interface ViewPresenter {
        fun setSearchSuccess(data: List<TranslateDTO>)
        fun setSearchError(errorText: String)
        fun setProcessLoading(isLoading: Boolean)
        fun setResultVisibility(isVisible: Boolean)
    }

    interface ViewViewModel {
        fun setSearchSuccess()
        fun setSearchError(errorText: String)
    }

    interface Presenter {
        fun viewAttach(view: ViewPresenter)
        fun viewDetach(view: ViewPresenter)
        fun getData(word: String)
    }

    abstract class ViewModel : androidx.lifecycle.ViewModel() {
        abstract fun onSearch(word: String)
        abstract val result: LiveData<List<TranslateDTO>>
        abstract val inProgress: LiveData<Boolean>
        abstract val onError: MutableLiveData<Throwable>
        abstract fun onRestore()
    }
}
package com.example.geekbrainstranslator.view

import androidx.lifecycle.LiveData
import com.example.geekbrainstranslator.data.entity.TranslateDTO

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

    interface ViewModel {
        fun onSearch(word: String)
        val result: LiveData<List<TranslateDTO>>
        val inProgress: LiveData<Boolean>
    }
}
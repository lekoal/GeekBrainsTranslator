package com.example.geekbrainstranslator.view

import com.example.geekbrainstranslator.data.entity.TranslateDTO

interface MainTranslationContract {
    interface View {
        fun setSearchSuccess(data: List<TranslateDTO>)
        fun setSearchError(errorText: String)
        fun setProcessLoading(isLoading: Boolean)
        fun setResultVisibility(isVisible: Boolean)
    }

    interface Presenter {
        fun viewAttach(view: View)
        fun viewDetach(view: View)
        fun getData(word: String)
    }

    interface ViewModel {
        fun onSearch(word: String)
    }
}
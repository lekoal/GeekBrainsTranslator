package com.example.geekbrainstranslator.view

interface MainTranslationContract {
    interface View {
        fun setSearchSuccess()
        fun setSearchError(errorText: String)
    }

    interface Presenter {
        fun onViewAttach(view: View)
        fun onViewDetach(view: View)
        fun onSearch(word: String)
    }
}
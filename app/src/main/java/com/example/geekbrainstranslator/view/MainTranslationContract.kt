package com.example.geekbrainstranslator.view

interface MainTranslationContract {
    interface View {
        fun setSearchSuccess()
        fun setSearchError(errorText: String)
    }

    interface Presenter {
        fun onSearch(word: String)
    }
}
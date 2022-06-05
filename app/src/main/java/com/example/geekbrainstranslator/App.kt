package com.example.geekbrainstranslator

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.geekbrainstranslator.data.RepoUsecaseImpl
import com.example.geekbrainstranslator.view.MainTranslationFragment
import com.example.geekbrainstranslator.view.MainTranslationPresenter
import com.example.geekbrainstranslator.view.MainTranslationRvAdapter
import com.example.geekbrainstranslator.view.MainTranslationViewModel

class App : Application() {

    val presenter by lazy { MainTranslationPresenter(repoUsecase) }
    val adapter by lazy { MainTranslationRvAdapter() }
    private val repoUsecase = RepoUsecaseImpl()
    val inputMethodManager by lazy { getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager }
}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app
package com.example.geekbrainstranslator

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.geekbrainstranslator.di.DaggerMainTranslationAppComponent
import com.example.geekbrainstranslator.di.MainTranslationAppComponent

class App : Application() {
    lateinit var mainTranslationAppComponent: MainTranslationAppComponent

    override fun onCreate() {
        super.onCreate()

        mainTranslationAppComponent = DaggerMainTranslationAppComponent
            .builder()
            .build()
    }

    val inputMethodManager by lazy { getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager }
}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app
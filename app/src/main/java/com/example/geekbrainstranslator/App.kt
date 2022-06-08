package com.example.geekbrainstranslator

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.geekbrainstranslator.di.remoteDataSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(remoteDataSourceModule))
        }
    }

    val inputMethodManager by lazy { getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager }
}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app
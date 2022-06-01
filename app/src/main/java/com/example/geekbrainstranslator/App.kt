package com.example.geekbrainstranslator

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.geekbrainstranslator.data.RepoUsecaseImpl
import com.example.geekbrainstranslator.domain.RepositoryUsecase

class App : Application() {
    val getTranslation: RepositoryUsecase by lazy { RepoUsecaseImpl() }
}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app
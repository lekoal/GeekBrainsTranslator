package com.example.geekbrainstranslator.di

import com.example.geekbrainstranslator.view.MainTranslationFragment
import com.example.geekbrainstranslator.view.viewmodel.MainTranslationViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MainTranslationAppModule::class
    ]
)


interface MainTranslationAppComponent {
    fun inject(mainTranslationFragment: MainTranslationFragment)
    fun viewModelFactory(): MainTranslationViewModelFactory
}
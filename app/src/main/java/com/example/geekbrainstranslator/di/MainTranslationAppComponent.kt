package com.example.geekbrainstranslator.di

import com.example.geekbrainstranslator.view.MainTranslationFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        MainTranslationAppModule::class,
        MainTranslationViewModelModule::class
    ]
)

@Singleton
@AppScope
interface MainTranslationAppComponent {
    fun inject(mainTranslationFragment: MainTranslationFragment)
}
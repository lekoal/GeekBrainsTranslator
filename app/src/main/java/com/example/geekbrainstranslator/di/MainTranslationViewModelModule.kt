package com.example.geekbrainstranslator.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.geekbrainstranslator.view.viewmodel.MainTranslationViewModel
import com.example.geekbrainstranslator.view.viewmodel.MainTranslationViewModelFactory
import com.example.geekbrainstranslator.view.viewmodel.MainTranslationViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class MainTranslationViewModelModule {

    @AppScope
    @Binds
    @IntoMap
    @MainTranslationViewModelKey(MainTranslationViewModel::class)
    abstract fun bindViewModel(
        mainTranslationViewModel: MainTranslationViewModel
    ): ViewModel

    @AppScope
    @Binds
    abstract fun bindViewModelFactory(
        vmFactory: MainTranslationViewModelFactory
    ): ViewModelProvider.Factory
}
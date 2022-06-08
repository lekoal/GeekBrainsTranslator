package com.example.geekbrainstranslator.di

import androidx.lifecycle.SavedStateHandle
import com.example.geekbrainstranslator.data.RepoUsecaseImpl
import com.example.geekbrainstranslator.domain.RepositoryUsecase
import com.example.geekbrainstranslator.domain.SkyengApi
import com.example.geekbrainstranslator.view.MainTranslationRvAdapter
import com.example.geekbrainstranslator.view.viewmodel.MainTranslationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val remoteDataSourceModule = module {
    single<MainTranslationRvAdapter> { MainTranslationRvAdapter() }
    single<SavedStateHandle> { SavedStateHandle() }
    single<SkyengApi> { get<Retrofit>().create(SkyengApi::class.java) }
    single<RepositoryUsecase> { RepoUsecaseImpl(get()) }
    factory<Converter.Factory> { GsonConverterFactory.create() }
    single<String>(named("base_url")) { "https://dictionary.skyeng.ru/api/public/v1/" }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get<String>(named("base_url")))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(get())
            .build()
    }
    viewModel { MainTranslationViewModel(get(), get()) }
}
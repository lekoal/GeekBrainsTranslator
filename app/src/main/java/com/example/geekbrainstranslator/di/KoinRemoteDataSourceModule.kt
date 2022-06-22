package com.example.geekbrainstranslator.di

import androidx.lifecycle.SavedStateHandle
import com.example.domain.RepositoryUsecase
import com.example.domain.SkyengApi
import com.example.geekbrainstranslator.data.remote.RepoUsecaseImpl
import com.example.geekbrainstranslator.view.main.MainTranslationFragment
import com.example.geekbrainstranslator.view.main.MainTranslationRvAdapter
import com.example.geekbrainstranslator.view.main.viewmodel.MainTranslationViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteDataSourceModule = module {

    single<SavedStateHandle>(named("main_save_state_handle")) {
        SavedStateHandle()
    }
    single<SkyengApi>(named("skyeng_api")) {
        get<Retrofit>().create(SkyengApi::class.java)
    }
    single<RepositoryUsecase>(named("repo_usecase")) {
        RepoUsecaseImpl(get(named("skyeng_api")), get(named("history_dao")))
    }
    factory<Converter.Factory>(named("gson_converter_factory")) {
        GsonConverterFactory.create()
    }
    single<String>(named("base_url")) {
        "https://dictionary.skyeng.ru/api/public/v1/"
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get<String>(named("base_url")))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(get(named("gson_converter_factory")))
            .build()
    }

    scope<MainTranslationFragment> {
        scoped(named("main_adapter")) {
            MainTranslationRvAdapter()
        }
        viewModel(named("main_view_model")) {
            MainTranslationViewModel(
                get(named("repo_usecase")),
                get(named("main_save_state_handle"))
            )
        }
    }
}
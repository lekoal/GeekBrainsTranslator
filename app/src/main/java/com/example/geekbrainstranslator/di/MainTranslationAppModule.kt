package com.example.geekbrainstranslator.di

import androidx.lifecycle.SavedStateHandle
import com.example.geekbrainstranslator.data.RepoUsecaseImpl
import com.example.geekbrainstranslator.domain.RepositoryUsecase
import com.example.geekbrainstranslator.domain.SkyengApi
import com.example.geekbrainstranslator.view.MainTranslationRvAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class MainTranslationAppModule {
    @Singleton
    @Provides
    fun provideAdapter(): MainTranslationRvAdapter {
        return MainTranslationRvAdapter()
    }

    @Singleton
    @Provides
    fun provideRepoUsecase(skyengApi: SkyengApi): RepositoryUsecase {
        return RepoUsecaseImpl(skyengApi)
    }

    @Singleton
    @Provides
    fun provideSavedStateHandle() : SavedStateHandle {
        return SavedStateHandle()
    }

    @Singleton
    @Provides
    fun provideSkyengApi(retrofit: Retrofit): SkyengApi {
        return retrofit.create(SkyengApi::class.java)
    }

    @AppScope
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String {
        return "https://dictionary.skyeng.ru/api/public/v1/"
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("base_url") baseUrl: String,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
            .build()
    }
}
package com.example.geekbrainstranslator.data

import com.example.geekbrainstranslator.data.entity.web.TranslateDTO
import com.example.geekbrainstranslator.domain.RepositoryUsecase
import com.example.geekbrainstranslator.domain.SkyengApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoUsecaseImpl(
    private val api: SkyengApi
) : RepositoryUsecase {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override suspend fun receiveAsync(word: String): Deferred<List<TranslateDTO>> {
        return api.searchAsync(word)
    }

    companion object {
        private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"
    }
}
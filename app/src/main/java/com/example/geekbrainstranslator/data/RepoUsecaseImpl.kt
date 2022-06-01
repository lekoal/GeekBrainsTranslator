package com.example.geekbrainstranslator.data

import com.example.geekbrainstranslator.data.entity.TranslateDTO
import com.example.geekbrainstranslator.domain.RepositoryUsecase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoUsecaseImpl : RepositoryUsecase {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dictionary.skyeng.ru/api/public/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: SkyengApi = retrofit.create(SkyengApi::class.java)

    override fun receive(word: String): List<TranslateDTO> {
        return api.search(word)
    }
}
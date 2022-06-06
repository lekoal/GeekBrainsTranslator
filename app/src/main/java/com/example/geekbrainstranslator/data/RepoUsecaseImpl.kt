package com.example.geekbrainstranslator.data

import com.example.geekbrainstranslator.data.entity.TranslateDTO
import com.example.geekbrainstranslator.domain.RepositoryUsecase
import com.example.geekbrainstranslator.domain.SkyengApi
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RepoUsecaseImpl : RepositoryUsecase {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dictionary.skyeng.ru/api/public/v1/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val api: SkyengApi = retrofit.create(SkyengApi::class.java)

    override fun receive(word: String): Observable<List<TranslateDTO>> {
        return api.search(word)
    }
}
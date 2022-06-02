package com.example.geekbrainstranslator.domain

import com.example.geekbrainstranslator.data.entity.TranslateDTO
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengApi {
    @GET("words/search")
    fun search(@Query("search") word: String): Observable<List<TranslateDTO>>
}
package com.example.geekbrainstranslator.domain

import com.example.geekbrainstranslator.data.entity.TranslateDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengApi {
    @GET("words/search")
    fun searchAsync(@Query("search") word: String): Deferred<List<TranslateDTO>>
}
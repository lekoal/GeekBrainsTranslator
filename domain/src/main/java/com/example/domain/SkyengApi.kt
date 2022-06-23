package com.example.domain

import com.example.data.entity.web.TranslateDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengApi {
    @GET("words/search")
    fun searchAsync(@Query("search") word: String): Deferred<List<TranslateDTO>>
}
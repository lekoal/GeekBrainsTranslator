package com.example.geekbrainstranslator.data

import com.example.geekbrainstranslator.data.entity.TranslateDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengApi {
    @GET("words/search")
    fun search(@Query("search") word: String): List<TranslateDTO>
}
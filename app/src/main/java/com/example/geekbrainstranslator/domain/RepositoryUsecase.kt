package com.example.geekbrainstranslator.domain

import com.example.geekbrainstranslator.data.entity.TranslateDTO
import io.reactivex.rxjava3.core.Observable

interface RepositoryUsecase {
    fun receive(word: String): Observable<List<TranslateDTO>>
}
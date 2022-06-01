package com.example.geekbrainstranslator.domain

import com.example.geekbrainstranslator.data.entity.TranslateDTO

interface RepositoryUsecase {
    fun receive(word: String): List<TranslateDTO>
}
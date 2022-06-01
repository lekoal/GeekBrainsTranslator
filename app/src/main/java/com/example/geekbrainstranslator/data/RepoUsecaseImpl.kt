package com.example.geekbrainstranslator.data

import com.example.geekbrainstranslator.data.entity.TranslateDTO
import com.example.geekbrainstranslator.domain.RepositoryUsecase

class RepoUsecaseImpl(
    private val api: SkyengApi
) : RepositoryUsecase {
    override fun receive(word: String): List<TranslateDTO> {
        return api.search(word)
    }
}
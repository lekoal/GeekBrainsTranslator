package com.example.geekbrainstranslator.data.entity


data class TranslateDTO(
    val id: Int,
    val meanings: List<Meaning>,
    val text: String
)
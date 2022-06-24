package com.example.utils

import com.example.data.entity.db.WordDataDetails

interface WordDataConverter {
    fun convert() : List<WordDataDetails>
}
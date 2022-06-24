package com.example.utils

import android.util.Log
import com.example.data.entity.db.WordData
import com.example.data.entity.db.WordDataDetails

class WordDataConverterImpl : WordDataConverter {
    var currentData: WordData? by DelegateWordData()
    private val tempData = mutableListOf<WordDataDetails>()
    var data: List<WordDataDetails> by DelegateWordDataDetails()

    override fun convert(): MutableList<WordDataDetails> {
        tempData.clear()
        for (i in 0 until (currentData?.transcription?.size!!)) {
            tempData.add(
                WordDataDetails(
                    id = currentData?.id,
                    imageUrl = currentData?.imageUrl?.get(i),
                    translation = currentData?.translation?.get(i),
                    transcription = currentData?.transcription?.get(i),
                    partOfSpeechCode = currentData?.partOfSpeechCode?.get(i)
                )
            )
        }
        data = tempData
        return tempData
    }
}
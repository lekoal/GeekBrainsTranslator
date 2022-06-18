package com.example.geekbrainstranslator.data.remote

import com.example.data.entity.db.WordData
import com.example.data.entity.db.dao.SearchHistoryDao
import com.example.data.entity.web.TranslateDTO
import com.example.domain.RepositoryUsecase
import com.example.domain.SkyengApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoUsecaseImpl(
    private val api: SkyengApi,
    private val historyDao: SearchHistoryDao
) : RepositoryUsecase {

    private val imageList = mutableListOf<String?>()
    private val transcription = mutableListOf<String?>()
    private val translation = mutableListOf<String?>()
    private val partOfSpeech = mutableListOf<String?>()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override suspend fun receiveAsync(word: String): Deferred<List<TranslateDTO>> {
        return api.searchAsync(word)
    }

    override fun addDataToDB(data: TranslateDTO) {
        data.meanings?.forEach { meaning ->
            imageList.add(meaning.imageUrl)
            transcription.add(meaning.transcription)
            partOfSpeech.add(meaning.partOfSpeechCode)
            translation.add(meaning.translation?.text)
        }
        historyDao.historyInsert(
            WordData(
                id = data.id,
                text = data.text,
                imageUrl = imageList,
                transcription = transcription,
                translation = translation,
                partOfSpeechCode = partOfSpeech
            )
        )
    }

    override suspend fun searchDataInDB(text: String) : WordData? {
        val result = historyDao.historyGetWord(text)
        return if (result.translation.isNotEmpty()) {
            result
        } else {
            null
        }
    }

    companion object {
        private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"
    }
}
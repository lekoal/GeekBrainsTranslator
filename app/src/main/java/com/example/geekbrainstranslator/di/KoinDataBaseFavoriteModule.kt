package com.example.geekbrainstranslator.di

import androidx.room.Room
import com.example.geekbrainstranslator.data.local.FavoriteWordsDataBase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val koinDataBaseFavoriteModule = module {
    single<FavoriteWordsDataBase>(named("favorite_words_data_base")) {
        Room.databaseBuilder(
            get(),
            FavoriteWordsDataBase::class.java,
            "favorite_words"
        )
            .build()
    }
    single(named("favorite_dao")) {
        get<FavoriteWordsDataBase>(
            named("favorite_words_data_base")
        )
            .favoriteWordsDao()
    }

}
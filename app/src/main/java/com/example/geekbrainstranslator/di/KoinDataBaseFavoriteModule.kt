package com.example.geekbrainstranslator.di

import com.example.geekbrainstranslator.data.FavoriteWordsDataBase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val koinDataBaseFavoriteModule = module {
    single(named("favorite_dao")) { get<FavoriteWordsDataBase>().favoriteWordsDao() }

}
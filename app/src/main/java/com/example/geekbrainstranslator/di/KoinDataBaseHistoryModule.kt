package com.example.geekbrainstranslator.di

import com.example.geekbrainstranslator.data.SearchHistoryDataBase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val koinDataBaseHistoryModule = module {
    single(named("history_dao")) { get<SearchHistoryDataBase>().searchHistoryDao() }

}
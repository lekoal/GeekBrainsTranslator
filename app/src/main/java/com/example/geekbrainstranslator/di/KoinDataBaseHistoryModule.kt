package com.example.geekbrainstranslator.di

import androidx.room.Room
import com.example.geekbrainstranslator.data.SearchHistoryDataBase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val koinDataBaseHistoryModule = module {
    single<SearchHistoryDataBase>(named("search_history_data_base")) {
        Room.databaseBuilder(
            get(),
            SearchHistoryDataBase::class.java,
            "search_history"
        )
            .build()
    }
    single(named("history_dao")) {
        get<SearchHistoryDataBase>(
            named("search_history_data_base")
        )
            .searchHistoryDao()
    }

}
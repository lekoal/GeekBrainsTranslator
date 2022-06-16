package com.example.geekbrainstranslator.di

import androidx.room.Room
import com.example.geekbrainstranslator.data.entity.db.dao.SearchHistoryDao
import com.example.geekbrainstranslator.data.local.SearchHistoryUsecaseImpl
import com.example.geekbrainstranslator.data.local.database.SearchHistoryDataBase
import com.example.geekbrainstranslator.domain.SearchHistoryUsecase
import com.example.geekbrainstranslator.view.story.SearchStoryRvAdapter
import com.example.geekbrainstranslator.view.story.viewmodel.SearchHistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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
    single<SearchHistoryDao>(named("history_dao")) {
        get<SearchHistoryDataBase>(
            named("search_history_data_base")
        )
            .searchHistoryDao()
    }
    single<SearchStoryRvAdapter>(named("search_history_adapter")) {
        SearchStoryRvAdapter()
    }
    single<SearchHistoryUsecase>(named("history_usecase_impl")) {
        SearchHistoryUsecaseImpl(get(named("history_dao")))
    }

    viewModel(named("search_history_view_model")) {
        SearchHistoryViewModel(
            get(named("history_usecase_impl"))
        )
    }
}
package com.example.geekbrainstranslator.di

import com.example.geekbrainstranslator.view.description.DescriptionWordFragment
import com.example.geekbrainstranslator.view.description.DescriptionWordRvAdapter
import com.example.geekbrainstranslator.view.description.viewmodel.DescriptionWordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val koinDataBaseDescriptionModule = module {
    scope<DescriptionWordFragment> {
        scoped(named("description_rv_adapter")) {
            DescriptionWordRvAdapter()
        }
        viewModel<DescriptionWordViewModel>(named("description_view_model")) {
            DescriptionWordViewModel(get(named("history_usecase_impl")))
        }
    }
}
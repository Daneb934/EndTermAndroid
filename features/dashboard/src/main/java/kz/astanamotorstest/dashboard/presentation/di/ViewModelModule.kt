package kz.astanamotorstest.dashboard.presentation.di

import kz.astanamotorstest.dashboard.presentation.ui.dashboard.favorite.FavoriteViewModel
import kz.astanamotorstest.dashboard.presentation.ui.dashboard.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieViewModelModule = module {
    viewModel { HomeViewModel(getPostsUseCase = get()) }
    viewModel { FavoriteViewModel(getFavoriteUseCase = get()) }
}

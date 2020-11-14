package kz.astanamotorstest.dashboard.presentation.di

import kz.astanamotorstest.dashboard.domain.usecase.favorite.GetFavoriteUseCase
import kz.astanamotorstest.dashboard.domain.usecase.movie.GetPopularityMoviesUseCase
import org.koin.dsl.module

val movieUseCaseModule = module {
    factory { GetPopularityMoviesUseCase(movieRepository = get()) }
    factory { GetFavoriteUseCase(favoriteRepository = get()) }
}



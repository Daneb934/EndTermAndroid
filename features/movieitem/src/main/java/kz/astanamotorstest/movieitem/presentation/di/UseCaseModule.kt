package kz.astanamotorstest.movieitem.presentation.di

import kz.astanamotorstest.movieitem.domain.usecase.AddFavoriteUseCase
import kz.astanamotorstest.movieitem.domain.usecase.DeleteFavoriteUseCase
import kz.astanamotorstest.movieitem.domain.usecase.GetMovieItemUseCase
import org.koin.dsl.module

val itemUseCaseModule = module {
    factory { GetMovieItemUseCase(movieItemRepository = get()) }
    factory { AddFavoriteUseCase(movieItemRepository = get()) }
    factory { DeleteFavoriteUseCase(movieItemRepository = get()) }
}
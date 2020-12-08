package kz.astanamotorstest.movieitem.presentation.di

import kz.astanamotorstest.movieitem.data.repository.DefaultMovieItemREpository
import kz.astanamotorstest.movieitem.domain.repository.MovieItemRepository
import org.koin.dsl.module

val itemRepositoryModule = module {
    single<MovieItemRepository> {
        DefaultMovieItemREpository(
            favoriteDao = get(),
            movieItemApi = get()
        )
    }
}
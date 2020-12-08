package kz.astanamotorstest.dashboard.presentation.di

import kz.astanamotorstest.dashboard.data.repository.DefaultFavoriteRepository
import kz.astanamotorstest.dashboard.data.repository.DefaultMovieRepository
import kz.astanamotorstest.dashboard.data.repository.DefaultPostRepository
import kz.astanamotorstest.dashboard.domain.repository.FavoriteRepository
import kz.astanamotorstest.dashboard.domain.repository.MovieRepository
import kz.astanamotorstest.dashboard.domain.repository.PostRepository
import org.koin.dsl.module

val movieRepositoryModule = module {
    single<MovieRepository> { DefaultMovieRepository(movieApi = get()) }
    single<FavoriteRepository> { DefaultFavoriteRepository(favoriteDao = get()) }
    single <PostRepository>{ DefaultPostRepository(movieApi = get()) }
}
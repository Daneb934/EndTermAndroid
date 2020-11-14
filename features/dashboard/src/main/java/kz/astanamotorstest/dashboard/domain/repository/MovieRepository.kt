package kz.astanamotorstest.dashboard.domain.repository

import kz.astanamotorstest.dashboard.domain.usecase.movie.GetPopularityMoviesUseCase
import kz.astanamotorstest.dashboard.entity.movie.MovieResponse
import kz.astanamotorstest.movieitem.data.local.entity.FavoriteRoom
import retrofit2.Response

interface MovieRepository {

    suspend fun getPopularityMovies(
        params: GetPopularityMoviesUseCase.Params
    ): Response<MovieResponse>

}
package kz.astanamotorstest.dashboard.data.repository

import kz.astanamotorstest.dashboard.data.remote.MovieApi
import kz.astanamotorstest.dashboard.domain.repository.MovieRepository
import kz.astanamotorstest.dashboard.domain.usecase.movie.GetPopularityMoviesUseCase
import kz.astanamotorstest.dashboard.entity.movie.MovieResponse
import kz.astanamotorstest.movieitem.data.local.dao.FavoriteDao
import kz.astanamotorstest.movieitem.data.local.entity.FavoriteRoom
import retrofit2.Response

class DefaultMovieRepository(
    private val movieApi: MovieApi
) : MovieRepository {

    override suspend fun getPopularityMovies(
        params: GetPopularityMoviesUseCase.Params
    ): Response<MovieResponse> = movieApi.getPopularMovies(
        api_key = params.api_key,
        language = params.language,
        page = params.page
    )
}
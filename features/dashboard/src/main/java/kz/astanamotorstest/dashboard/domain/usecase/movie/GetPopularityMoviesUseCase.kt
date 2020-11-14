package kz.astanamotorstest.dashboard.domain.usecase.movie

import kz.astanamotorstest.dashboard.domain.repository.MovieRepository
import kz.astanamotorstest.dashboard.entity.movie.MovieResponse
import kz.astanamotorstest.dashboard.entity.movie.MovieResponseUi
import kz.astanamotorstest.dashboard.entity.movie.MovieUi
import kz.astanamotorstest.network_components.BaseUseCase
import kz.astanamotorstest.network_components.Either
import kz.astanamotorstest.network_components.Failure
import timber.log.Timber

class GetPopularityMoviesUseCase(
    private val movieRepository: MovieRepository
) : BaseUseCase<MovieResponseUi, GetPopularityMoviesUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, MovieResponseUi> {
        return try {
            val response = movieRepository.getPopularityMovies(params = params)
            when (response.isSuccessful) {
                true -> Either.Right(mapMovieResponse(response.body()!!))
                false -> Either.Left(Failure.FeatureFailure())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Left(Failure.NetworkConnection)
        }
    }

    private fun mapMovieResponse(input: MovieResponse): MovieResponseUi {
        Timber.i("mapMovieResponse")
        return MovieResponseUi(
            page = input.page,
            total_results = input.total_results,
            total_pages = input.total_pages,
            results = input.results.map {
                MovieUi(
                    id = it.id,
                    popularity = it.popularity,
                    vote_count = it.vote_count,
                    video = it.video,
                    poster_path = it.poster_path,
                    adult = it.adult,
                    backdrop_path = it.backdrop_path ?: "",
                    original_language = it.original_language,
                    original_title = it.original_title,
                    genre_ids = it.genre_ids,
                    title = it.title,
                    vote_average = it.vote_average,
                    overview = it.overview,
                    release_date = it.release_date
                )
            }
        )
    }


    data class Params(
        val api_key: String = "c043f1a46baa8e7580be6e9bd9fa6b14",
        val language: String = "en-US",
        var page: Int
    )
}
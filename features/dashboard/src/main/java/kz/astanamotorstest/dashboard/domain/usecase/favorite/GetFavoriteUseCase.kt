package kz.astanamotorstest.dashboard.domain.usecase.favorite

import kz.astanamotorstest.dashboard.domain.repository.FavoriteRepository
import kz.astanamotorstest.dashboard.entity.movie.MovieUi
import kz.astanamotorstest.movieitem.data.local.entity.FavoriteRoom
import kz.astanamotorstest.network_components.BaseUseCase
import kz.astanamotorstest.network_components.Either
import kz.astanamotorstest.network_components.Failure
import timber.log.Timber

class GetFavoriteUseCase(
    private val favoriteRepository: FavoriteRepository
) : BaseUseCase<List<MovieUi>, Boolean>() {

    override suspend fun run(params: Boolean): Either<Failure, List<MovieUi>> =
        try {
            Either.Right(mapMovies(input = favoriteRepository.getFavorites()))
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Left(Failure.FeatureFailure())
        }

    private fun mapMovies(input: List<FavoriteRoom>): List<MovieUi> {
        Timber.i("mapMovieResponse")
        return input.map {
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
                genre_ids = listOf(),
                title = it.title,
                vote_average = it.vote_average,
                overview = it.overview,
                release_date = it.release_date
            )
        }
    }
}
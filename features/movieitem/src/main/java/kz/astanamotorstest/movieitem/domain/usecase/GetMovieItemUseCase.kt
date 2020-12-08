package kz.astanamotorstest.movieitem.domain.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kz.astanamotorstest.movieitem.domain.repository.MovieItemRepository
import kz.astanamotorstest.movieitem.entity.general.GenreUi
import kz.astanamotorstest.movieitem.entity.general.LanguageUi
import kz.astanamotorstest.movieitem.entity.movieitem.MovieDto
import kz.astanamotorstest.movieitem.entity.movieitem.MovieUi
import kz.astanamotorstest.movieitem.entity.production.ProductionCompanyUi
import kz.astanamotorstest.movieitem.entity.production.ProductionCountryUi
import kz.astanamotorstest.network_components.BaseUseCase
import kz.astanamotorstest.network_components.Either
import kz.astanamotorstest.network_components.Failure
import timber.log.Timber

class GetMovieItemUseCase(
    private val movieItemRepository: MovieItemRepository
) : BaseUseCase<MovieUi, GetMovieItemUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, MovieUi> {
        return try {
            val response = movieItemRepository.getMovieItem(params = params)
            when (response.isSuccessful) {
                true -> Either.Right(mapMovieUi(response.body()!!))
                false -> Either.Left(Failure.FeatureFailure())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Left(Failure.NetworkConnection)
        }
    }

    suspend fun getFavorite() = movieItemRepository.getFavorite()

    override fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (Either<Failure, MovieUi>) -> Unit
    ) {
        val backgroundJob = scope.async {
            run(params)
        }

        val localJob = scope.async {
            getFavorite()
        }

        scope.launch {
            val movieJob = backgroundJob.await()
            val favoriteJob = localJob.await()
            if (movieJob is Either.Right) {
                favoriteJob.find { it.id == movieJob.b.id }?.let {
                    movieJob.b.isFavorite = true
                }
            }
            onResult(movieJob)
        }
    }

    private fun mapMovieUi(input: MovieDto): MovieUi {
        Timber.i("mapMovieUi, input = &input")
        return MovieUi(
            id = input.id,
            adult = input.adult,
            backdrop_path = input.backdrop_path ?: "",
            budget = input.budget,
            genres = input.genres.map {
                GenreUi(
                    id = it.id,
                    name = it.name
                )
            },
            homepage = input.homepage ?: "",
            imdb_id = input.imdb_id,
            original_language = input.original_language,
            original_title = input.original_title,
            overview = input.overview,
            popularity = input.popularity,
            poster_path = input.poster_path,
            production_companies = input.production_companies?.map {
                ProductionCompanyUi(
                    id = it.id,
                    logo_path = it.logo_path ?: "",
                    name = it.name,
                    origin_country = it.origin_country
                )
            } ?: listOf(),
            production_countries = input.production_countries?.map {
                ProductionCountryUi(
                    iso_3166_1 = it.iso_3166_1,
                    name = it.name
                )
            } ?: listOf(),
            release_date = input.release_date,
            revenue = input.revenue,
            runtime = input.runtime,
            spoken_languages = input.spoken_languages?.map {
                LanguageUi(
                    iso_639_1 = it.iso_639_1,
                    name = it.name
                )
            } ?: listOf(),
            status = input.status,
            tagline = input.tagline,
            title = input.title,
            video = input.video,
            vote_average = input.vote_average,
            vote_count = input.vote_count
        )
    }


    data class Params(
        var movieId: Long? = null,
        val api_key: String = "c043f1a46baa8e7580be6e9bd9fa6b14",
        val language: String = "en-US"
    )
}
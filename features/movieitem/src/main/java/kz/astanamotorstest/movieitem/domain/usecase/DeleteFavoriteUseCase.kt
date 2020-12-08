package kz.astanamotorstest.movieitem.domain.usecase

import kz.astanamotorstest.movieitem.domain.repository.MovieItemRepository
import kz.astanamotorstest.network_components.BaseUseCase
import kz.astanamotorstest.network_components.Either
import kz.astanamotorstest.network_components.Failure

class DeleteFavoriteUseCase(
    private val movieItemRepository: MovieItemRepository
) : BaseUseCase<Unit, Long>() {

    override suspend fun run(params: Long): Either<Failure, Unit> =
        try {
            Either.Right(movieItemRepository.deleteFavorite(params))
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Left(Failure.FeatureFailure())
        }
}
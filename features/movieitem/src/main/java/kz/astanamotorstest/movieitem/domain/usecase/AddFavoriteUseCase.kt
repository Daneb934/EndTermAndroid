package kz.astanamotorstest.movieitem.domain.usecase

import kz.astanamotorstest.movieitem.data.local.entity.FavoriteRoom
import kz.astanamotorstest.movieitem.domain.repository.MovieItemRepository
import kz.astanamotorstest.network_components.BaseUseCase
import kz.astanamotorstest.network_components.Either
import kz.astanamotorstest.network_components.Failure

class AddFavoriteUseCase(
    private val movieItemRepository: MovieItemRepository
) : BaseUseCase<Unit, FavoriteRoom>() {

    override suspend fun run(params: FavoriteRoom): Either<Failure, Unit> =
        try {
            Either.Right(movieItemRepository.addFavorite(params))
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Left(Failure.FeatureFailure())
        }
}
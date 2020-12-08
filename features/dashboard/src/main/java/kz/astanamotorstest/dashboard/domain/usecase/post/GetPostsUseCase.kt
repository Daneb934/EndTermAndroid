package kz.astanamotorstest.dashboard.domain.usecase.post

import kz.astanamotorstest.dashboard.domain.repository.PostRepository
import kz.astanamotorstest.dashboard.entity.movie.post.PostUi
import kz.astanamotorstest.network_components.BaseUseCase
import kz.astanamotorstest.network_components.Either
import kz.astanamotorstest.network_components.Failure

class GetPostsUseCase(
    private val postRepository: PostRepository
): BaseUseCase<List<PostUi>, Boolean>() {
    override suspend fun run(params: Boolean): Either<Failure, List<PostUi>> {
        return try {
            val response = postRepository.getPosts()
            when(response.isSuccessful){
                true -> Either.Right(response.body()?.map { PostUi(userId = it.userId, id = it.id, title = it.title, body = it.body) }?: listOf())
                false -> Either.Left(Failure.FeatureFailure())
            }
        }catch (e: Exception){
            e.printStackTrace()
            Either.Left(Failure.NetworkConnection)
        }
    }
}
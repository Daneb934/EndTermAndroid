package kz.astanamotorstest.dashboard.data.repository

import kz.astanamotorstest.dashboard.data.remote.MovieApi
import kz.astanamotorstest.dashboard.domain.repository.PostRepository
import kz.astanamotorstest.dashboard.entity.movie.post.PostDto
import retrofit2.Response

class DefaultPostRepository (
    private val movieApi: MovieApi
): PostRepository {

    override suspend fun getPosts(): Response<List<PostDto>> =
        movieApi.getPosts()
}
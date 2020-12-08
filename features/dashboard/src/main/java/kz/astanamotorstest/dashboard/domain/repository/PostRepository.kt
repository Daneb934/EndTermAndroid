package kz.astanamotorstest.dashboard.domain.repository

import kz.astanamotorstest.dashboard.entity.movie.post.PostDto
import retrofit2.Response

interface PostRepository {

    suspend fun getPosts(): Response<List<PostDto>>
}
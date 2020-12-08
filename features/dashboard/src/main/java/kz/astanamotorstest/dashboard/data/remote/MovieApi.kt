package kz.astanamotorstest.dashboard.data.remote

import kz.astanamotorstest.dashboard.entity.movie.MovieResponse
import kz.astanamotorstest.dashboard.entity.movie.post.PostDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_MOVIE = "/3/movie"

interface MovieApi {

    @GET("$BASE_MOVIE/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("https://jsonplaceholder.typicode.com/posts")
    suspend fun getPosts(): Response<List<PostDto>>
}
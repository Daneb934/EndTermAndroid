package kz.astanamotorstest.movieitem.data.remote

import kz.astanamotorstest.movieitem.entity.movieitem.MovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_MOVIE = "/3/movie"

interface MovieItemApi {

    @GET("$BASE_MOVIE/{movieId}")
    suspend fun getMovieItem(
        @Path("movieId") movieId: Long,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Response<MovieDto>
}
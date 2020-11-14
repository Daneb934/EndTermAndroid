package kz.astanamotorstest.movieitem.data.repository

import kz.astanamotorstest.movieitem.data.local.dao.FavoriteDao
import kz.astanamotorstest.movieitem.data.local.entity.FavoriteRoom
import kz.astanamotorstest.movieitem.data.remote.MovieItemApi
import kz.astanamotorstest.movieitem.domain.repository.MovieItemRepository
import kz.astanamotorstest.movieitem.domain.usecase.GetMovieItemUseCase
import kz.astanamotorstest.movieitem.entity.movieitem.MovieDto
import retrofit2.Response

class DefaultMovieItemREpository(
    private val favoriteDao: FavoriteDao,
    private val movieItemApi: MovieItemApi
) : MovieItemRepository {

    override suspend fun getMovieItem(
        params: GetMovieItemUseCase.Params
    ): Response<MovieDto> = movieItemApi.getMovieItem(
        movieId = params.movieId ?: 0,
        api_key = params.api_key,
        language = params.language
    )

    override suspend fun getFavorite(): List<FavoriteRoom> = favoriteDao.getFavorites()

    override suspend fun addFavorite(favoriteRoom: FavoriteRoom) {
        favoriteDao.insert(favoriteRoom)
    }

    override suspend fun deleteFavorite(id: Long) {
        favoriteDao.deleteFromFavorite(id)
    }
}
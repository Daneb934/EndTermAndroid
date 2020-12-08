package kz.astanamotorstest.movieitem.domain.repository

import kz.astanamotorstest.movieitem.data.local.entity.FavoriteRoom
import kz.astanamotorstest.movieitem.domain.usecase.GetMovieItemUseCase
import kz.astanamotorstest.movieitem.entity.movieitem.MovieDto
import retrofit2.Response

interface MovieItemRepository {

    suspend fun getMovieItem(params: GetMovieItemUseCase.Params):
            Response<MovieDto>

    suspend fun getFavorite(): List<FavoriteRoom>

    suspend fun addFavorite(favoriteRoom: FavoriteRoom)

    suspend fun deleteFavorite(id: Long)
}
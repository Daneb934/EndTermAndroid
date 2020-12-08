package kz.astanamotorstest.dashboard.data.repository

import kz.astanamotorstest.dashboard.domain.repository.FavoriteRepository
import kz.astanamotorstest.movieitem.data.local.dao.FavoriteDao
import kz.astanamotorstest.movieitem.data.local.entity.FavoriteRoom

class DefaultFavoriteRepository(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {

    override suspend fun getFavorites(): List<FavoriteRoom> =
        favoriteDao.getFavorites()
}
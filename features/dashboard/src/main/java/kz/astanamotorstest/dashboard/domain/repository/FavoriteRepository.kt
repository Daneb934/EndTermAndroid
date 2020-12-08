package kz.astanamotorstest.dashboard.domain.repository

import kz.astanamotorstest.movieitem.data.local.entity.FavoriteRoom

interface FavoriteRepository {

    suspend fun getFavorites(): List<FavoriteRoom>
}
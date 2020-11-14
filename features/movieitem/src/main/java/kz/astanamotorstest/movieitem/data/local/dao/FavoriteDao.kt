package kz.astanamotorstest.movieitem.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.astanamotorstest.movieitem.data.local.entity.FavoriteRoom

@Dao
abstract class FavoriteDao{

    @Query("SELECT * FROM FAVORITES")
    abstract suspend fun getFavorites(): List<FavoriteRoom>

    @Query("DELETE FROM FAVORITES")
    abstract suspend fun deleteFavorites()

    @Insert
    abstract suspend fun addAll(favorites: List<FavoriteRoom>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(data: FavoriteRoom)

    @Query("DELETE FROM FAVORITES WHERE ID = :id")
    abstract suspend fun deleteFromFavorite(id: Long)

}
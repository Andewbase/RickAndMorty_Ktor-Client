package com.example.rickandmorty.data.cache.favorite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.cache.favorite.entity.FavoriteDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(favoriteDBO: FavoriteDBO)

    @Query("SELECT * FROM favorite")
    fun getAllFavorites(): Flow<FavoriteDBO>

    @Delete
    suspend fun deleteFavorite(favoriteDBO: FavoriteDBO)
}
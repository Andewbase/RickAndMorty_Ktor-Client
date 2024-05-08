package com.example.rickandmorty.data

import com.example.rickandmorty.data.cache.favorite.FavoriteDao
import com.example.rickandmorty.domain.Favorite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface FavoriteRepository {

    fun getAllFavorites(): Flow<List<Favorite>>

    suspend fun saveFavorite(favorite: Favorite)

    suspend fun deleteFavorite(favorite: Favorite)

    class Base @Inject constructor(
        private val favoriteDao: FavoriteDao
    ): FavoriteRepository{
        override fun getAllFavorites(): Flow<List<Favorite>> {
           return favoriteDao.getAllFavorites().map { list ->
               list.map {
                   it.toFavorite()
               }
           }
        }

        override suspend fun saveFavorite(favorite: Favorite) {
            favoriteDao.saveFavorite(favorite.toFavoriteDBO())
        }

        override suspend fun deleteFavorite(favorite: Favorite) {
            favoriteDao.deleteFavorite(favorite.toFavoriteDBO())
        }

    }

}
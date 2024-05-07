package com.example.rickandmorty.data.cache.favorite.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite",
    indices = [
        Index("name", unique = true)
    ]
)
data class FavoriteDBO(
    @ColumnInfo("created") val created: String,
    @ColumnInfo("episode") val episode: List<String>,
    @ColumnInfo("gender") val gender: String,
    @PrimaryKey @ColumnInfo("id") val id: Int,
    @ColumnInfo("image") val image: String,
    @Embedded(prefix = "location_") val location: FavoriteLocationDBO,
    @ColumnInfo("name") val name: String,
    @Embedded(prefix = "origin_") val origin: FavoriteOriginDBO,
    @ColumnInfo("species") val species: String,
    @ColumnInfo("status") val status: String,
    @ColumnInfo("type") val type: String,
    @ColumnInfo("url") val url: String,
    @ColumnInfo("isFavorite") val isFavorite: Boolean
)

data class FavoriteLocationDBO(
    val name: String,
    val url: String
)

data class FavoriteOriginDBO(
    val name: String,
    val url: String
)


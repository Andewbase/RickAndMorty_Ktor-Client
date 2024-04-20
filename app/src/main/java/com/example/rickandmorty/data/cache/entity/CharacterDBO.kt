package com.example.rickandmorty.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "character",
    indices = [
        Index("name", unique = true)
    ]
)
data class CharacterDBO(
    @ColumnInfo("created") val created: String,
    @ColumnInfo("episode") val episode: List<String>,
    @ColumnInfo("gender") val gender: String,
    @PrimaryKey @ColumnInfo("id") val id: Int,
    @ColumnInfo("image") val image: String,
    @ColumnInfo("location") @Embedded val location: LocationDBO,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("origin") @Embedded val origin: OriginDBO,
    @ColumnInfo("species") val species: String,
    @ColumnInfo("status") val status: String,
    @ColumnInfo("type") val type: String,
    @ColumnInfo("url") val url: String
)

data class LocationDBO(
    @ColumnInfo("location_name") val name: String,
    @ColumnInfo("location_url") val url: String
)

data class OriginDBO(
    @ColumnInfo("origin_name") val name: String,
    @ColumnInfo("origin_url") val url: String
)

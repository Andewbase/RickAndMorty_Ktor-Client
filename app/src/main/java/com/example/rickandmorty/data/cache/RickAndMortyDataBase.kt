package com.example.rickandmorty.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.data.cache.character.CharacterDao
import com.example.rickandmorty.data.cache.character.entity.CharacterDBO
import com.example.rickandmorty.data.cache.favorite.FavoriteDao
import com.example.rickandmorty.data.cache.favorite.entity.FavoriteDBO
import com.example.rickandmorty.data.cache.util.Converters

@Database(entities = [CharacterDBO::class, FavoriteDBO::class], version = 1)
@TypeConverters(Converters::class)
abstract class RickAndMortyDataBase: RoomDatabase() {
    abstract fun getCharacterDao(): CharacterDao
    abstract fun getFavoriteDao(): FavoriteDao
}

package com.example.rickandmorty.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.data.cache.entity.CharacterDBO
import com.example.rickandmorty.data.cache.util.Converters

@Database(entities = [CharacterDBO::class], version = 1)
@TypeConverters(Converters::class)
abstract class RickAndMortyDataBase: RoomDatabase() {
    abstract fun getCharacterDao(): CharacterDao
}

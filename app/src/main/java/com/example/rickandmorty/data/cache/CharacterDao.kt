package com.example.rickandmorty.data.cache

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.cache.entity.CharacterDBO
import kotlinx.coroutines.flow.Flow

interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(characters: List<CharacterDBO>)

    @Query("SELECT * FROM character")
    fun getAllCharacters(): Flow<List<CharacterDBO>>

    @Query("SELECT * FROM character WHERE id = :id")
    fun getCharacterById(id: Int): Flow<CharacterDBO>

    @Query("SELECT * FROM character WHERE name = :name")
    fun getCharacterByName(name: String): Flow<CharacterDBO>

    @Query("DELETE FROM character")
    fun removeOldData()

}
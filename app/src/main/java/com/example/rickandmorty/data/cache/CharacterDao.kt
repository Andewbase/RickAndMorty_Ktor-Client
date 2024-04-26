package com.example.rickandmorty.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.cache.entity.CharacterDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(characters: List<CharacterDBO>)

    @Query("SELECT * FROM character")
    suspend fun getAllCharacters(): List<CharacterDBO>

    @Query("SELECT * FROM character")
    fun observeAll(): Flow<List<CharacterDBO>>

    @Query("SELECT * FROM character WHERE id = :id")
    fun getCharacterById(id: Int): Flow<CharacterDBO>

    @Query("SELECT * FROM character WHERE name = :name")
    fun getCharacterByName(name: String): Flow<CharacterDBO>

    @Query("DELETE FROM character")
    fun removeOldData()

}
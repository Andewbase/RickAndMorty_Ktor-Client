package com.example.rickandmorty.data

import com.example.rickandmorty.data.cache.CharacterDao
import com.example.rickandmorty.data.network.RickAndMortyApi
import com.example.rickandmorty.domain.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

interface RickAndMortyRepository {

    fun getAll(
        mergeStrategy: MergeStrategy<RequestResult<List<Character>>> = ResultMergeStrategy()
    ): Flow<List<Character>>

    fun getAllDataBase(): Flow<List<Character>>


    @Singleton
    class Base @Inject constructor(
        private val api: RickAndMortyApi,
        private val dao: CharacterDao
    ): RickAndMortyRepository{
        override fun getAll(
            mergeStrategy: MergeStrategy<RequestResult<List<Character>>>
        ): Flow<List<Character>> {
           return
        }

        override fun getAllDataBase(): Flow<List<Character>> {
            return dao.getAllCharacters().map { list ->
                list.map { character ->
                    character.toCharacter()
                }
            }
        }

    }

}
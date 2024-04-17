package com.example.rickandmorty.data

import com.example.rickandmorty.data.network.HttpClientResult
import com.example.rickandmorty.data.network.RickAndMortyApi
import com.example.rickandmorty.domain.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface RickAndMortyRepository {

   /* suspend fun getAll(): Character
    @Singleton
    class Base @Inject constructor(private val api: RickAndMortyApi): RickAndMortyRepository{
        override suspend fun getAll(): Character {
            when (val apiResult = api.getCharacter(1)){
                is HttpClientResult.Success -> return apiResult.data.characterDTO.toCharacter()
                is HttpClientResult.Error -> println()
            }
        }

    }*/

}
package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.RequestResult
import com.example.rickandmorty.domain.Character

interface RickAndMortyApi {

    suspend fun getCharacter(page: Int): RequestResult<List<Character>>

}
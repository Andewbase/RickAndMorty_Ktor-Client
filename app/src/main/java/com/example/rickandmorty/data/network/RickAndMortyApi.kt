package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.network.entity.GetCharactersDTO

interface RickAndMortyApi {

    suspend fun getCharacter(page: Int): HttpClientResult<GetCharactersDTO>

}
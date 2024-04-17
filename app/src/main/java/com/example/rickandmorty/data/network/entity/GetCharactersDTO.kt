package com.example.rickandmorty.data.network.entity

import kotlinx.serialization.Serializable

@Serializable
data class GetCharactersDTO(
    val info: InfoDTO,
    val characterDTO: CharacterDTO
)

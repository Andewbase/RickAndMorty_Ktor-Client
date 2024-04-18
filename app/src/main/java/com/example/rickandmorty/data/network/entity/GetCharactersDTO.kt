package com.example.rickandmorty.data.network.entity

import kotlinx.serialization.Serializable

@Serializable
data class GetCharactersDTO(
    val info: InfoDTO,
    val results: List<CharacterDTO>
){
    val characters get() = this.results.map { it.toCharacter() }
}

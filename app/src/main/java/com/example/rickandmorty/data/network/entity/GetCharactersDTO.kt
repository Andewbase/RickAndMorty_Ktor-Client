package com.example.rickandmorty.data.network.entity

import kotlinx.serialization.Serializable

@Serializable
data class GetCharactersDTO(
    val info: InfoDTO,
    val result: List<CharacterDTO>
){
    val characters get() = this.result.map { it.toCharacter() }
}

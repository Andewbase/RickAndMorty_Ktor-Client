package com.example.rickandmorty.data.network.entity

import com.example.rickandmorty.domain.Character
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDTO(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val locationDTO: LocationDTO,
    val name: String,
    val originDTO: OriginDTO,
    val species: String,
    val status: String,
    val type: String,
    val url: String
){
    fun toCharacter(): Character{
        return Character(
            created = created,
            episode = episode,
            gender = gender,
            id = id,
            image = image,
            locationDTO = locationDTO.toLocation(),
            name = name,
            originDTO = originDTO.toOrigin(),
            species = species,
            status = status,
            type = type,
            url = url
        )
    }
}
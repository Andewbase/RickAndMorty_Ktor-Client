package com.example.rickandmorty.network.entity

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
)
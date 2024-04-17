package com.example.rickandmorty.network.entity

import kotlinx.serialization.Serializable

@Serializable
data class LocationDTO(
    val name: String,
    val url: String
)
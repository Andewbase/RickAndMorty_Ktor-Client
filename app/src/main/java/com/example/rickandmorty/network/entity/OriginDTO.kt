package com.example.rickandmorty.network.entity

import kotlinx.serialization.Serializable

@Serializable
data class OriginDTO(
    val name: String,
    val url: String
)
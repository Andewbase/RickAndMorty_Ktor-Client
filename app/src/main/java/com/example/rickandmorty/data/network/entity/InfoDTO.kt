package com.example.rickandmorty.data.network.entity

import kotlinx.serialization.Serializable

@Serializable
data class InfoDTO(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?
)
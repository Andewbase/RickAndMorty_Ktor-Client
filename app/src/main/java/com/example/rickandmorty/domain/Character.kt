package com.example.rickandmorty.domain

data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val locationDTO: Location,
    val name: String,
    val originDTO: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

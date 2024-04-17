package com.example.rickandmorty.data.network.entity

import com.example.rickandmorty.domain.Location
import kotlinx.serialization.Serializable

@Serializable
data class LocationDTO(
    val name: String,
    val url: String
){
    fun toLocation(): Location{
        return Location(
            name = name,
            url = url
        )
    }
}
package com.example.rickandmorty.data.network.entity

import com.example.rickandmorty.domain.Origin
import kotlinx.serialization.Serializable

@Serializable
data class OriginDTO(
    val name: String,
    val url: String
){
    fun toOrigin(): Origin{
        return Origin(
            name = name,
            url = url
        )
    }
}
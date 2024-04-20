package com.example.rickandmorty.data

import com.example.rickandmorty.data.cache.entity.CharacterDBO
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.Location
import com.example.rickandmorty.domain.Origin

fun CharacterDBO.toCharacter(): Character{
    return Character(
        created = this.created,
        episode = this.episode,
        gender = this.gender,
        id = this.id,
        image = this.image,
        locationDTO = Location(
            name = this.location.name,
            url = this.location.url
        ),
        name = this.name,
        originDTO = Origin(
            name = this.origin.name,
            url = this.origin.url
        ),
        species = this.species,
        status = this.status,
        type = this.type,
        url = this.url
    )
}
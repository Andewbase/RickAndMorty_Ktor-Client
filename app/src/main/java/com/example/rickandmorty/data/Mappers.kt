package com.example.rickandmorty.data

import com.example.rickandmorty.data.cache.character.entity.CharacterDBO
import com.example.rickandmorty.data.cache.character.entity.LocationDBO
import com.example.rickandmorty.data.cache.character.entity.OriginDBO
import com.example.rickandmorty.data.cache.favorite.entity.FavoriteDBO
import com.example.rickandmorty.data.cache.favorite.entity.FavoriteLocationDBO
import com.example.rickandmorty.data.cache.favorite.entity.FavoriteOriginDBO
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.Favorite
import com.example.rickandmorty.domain.Location
import com.example.rickandmorty.domain.Origin

fun CharacterDBO.toCharacter(): Character{
    return Character(
        created = this.created,
        episode = this.episode,
        gender = this.gender,
        id = this.id,
        image = this.image,
        location = Location(
            name = this.location.name,
            url = this.location.url
        ),
        name = this.name,
        origin = Origin(
            name = this.origin.name,
            url = this.origin.url
        ),
        species = this.species,
        status = this.status,
        type = checkType(this.type) ,
        url = this.url,
        isFavorite = this.isFavorite
    )
}

fun Character.toCharacterDBO(): CharacterDBO {
    return CharacterDBO(
        created = this.created,
        episode = this.episode,
        gender = this.gender,
        id = this.id,
        image = this.image,
        location = LocationDBO(
            name = this.location.name,
            url = this.location.url
        ),
        name = this.name,
        origin= OriginDBO (
            name = this.origin.name,
            url = this.origin.url
        ),
        species = this.species,
        status = this.status,
        type = this.type,
        url = this.url,
        isFavorite = this.isFavorite
    )
}

fun FavoriteDBO.toFavorite(): Favorite{
    return Favorite(
        created = this.created,
        episode = this.episode,
        gender = this.gender,
        id = this.id,
        image = this.image,
        location = Location(
            name = this.location.name,
            url = this.location.url
        ),
        name = this.name,
        origin = Origin(
            name = this.origin.name,
            url = this.origin.url
        ),
        species = this.species,
        status = this.status,
        type = this.type,
        url = this.url,
        isFavorite = this.isFavorite
    )
}

fun Favorite.toFavoriteDBO(): FavoriteDBO{
    return FavoriteDBO(
        created = this.created,
        episode = this.episode,
        gender = this.gender,
        id = this.id,
        image = this.image,
        location = FavoriteLocationDBO(
            name = this.location.name,
            url = this.location.url
        ),
        name = this.name,
        origin= FavoriteOriginDBO (
            name = this.origin.name,
            url = this.origin.url
        ),
        species = this.species,
        status = this.status,
        type = this.type,
        url = this.url,
        isFavorite = this.isFavorite
    )
}

private fun checkType(type: String): String{
  return  if (type.isBlank()){
       "No information available"
    }else{
        type
    }
}
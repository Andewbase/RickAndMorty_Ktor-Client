package com.example.rickandmorty.screen.characters

import com.example.rickandmorty.domain.Character

sealed interface MainEvent {

    data class UpdateFavorite(val isFavorite: Boolean): MainEvent
    data class GetCharacter(val character: Character): MainEvent
}
package com.example.rickandmorty.screen.detail

sealed interface DetailEvent {

    data class UpdateFavorite(val isFavorite: Boolean): DetailEvent

}
package com.example.rickandmorty.screen.characters

sealed class MainState {
    object None: MainState()
    class Loading(val characters: List<CharacterItemUI>? = null): MainState()
    class Error(val characters: List<CharacterItemUI>? = null) : MainState()
    class Success(val characters: List<CharacterItemUI>): MainState()

}
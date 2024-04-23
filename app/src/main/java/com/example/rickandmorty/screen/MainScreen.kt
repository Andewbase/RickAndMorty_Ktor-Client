package com.example.rickandmorty.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MainScreen (state: State){
    when (state){
        is State.Success -> ShowContent(state = state)
        is State.Loading -> ShowContent(state = state)
        is State.Error -> ShowContent(state = state)
        State.None -> Text(text = "None")
    }
}

@Composable
private fun ShowContent(state: State.Success){
    val characters = state.characters
    LazyColumn {
        items(
            items = characters,
            key = {character -> character.id}
        ) {character ->
            Item(characterUI = character)
        }
    }
}

@Composable
private fun ShowContent(state: State.Loading){
    val characters = state.characters
    if (characters != null){
        LazyColumn {
            items(
                items = characters,
                key = {character -> character.id}
            ) {character ->
                Item(characterUI = character)
            }
        }
    }else{
        Text(text = "Loading")
    }
}

@Composable
private fun ShowContent(state: State.Error){
    val characters = state.characters
    if (characters != null){
        LazyColumn {
            items(
                items = characters,
                key = {character -> character.id}
            ) {character ->
                Item(characterUI = character)
            }
        }
    }else{
        Text(text = "Error")
    }
}
@Composable
private fun Item(characterUI: CharacterUI){
    Column {
        Text(text = characterUI.name)
    }
}

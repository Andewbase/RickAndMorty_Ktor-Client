package com.example.rickandmorty.screen.characters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.rickandmorty.Constant
import com.example.rickandmorty.navigation.AppScreens

@Composable
fun MainScreen (
    state: State,
    navController: NavController,
    modifier: Modifier = Modifier
){
    when (state){
        is State.Success -> ShowContent(state = state, navController = navController, modifier = modifier)
        is State.Loading -> ShowContent(state = state, navController = navController, modifier = modifier)
        is State.Error -> ShowContent(state = state, navController = navController, modifier = modifier)
        State.None -> Text(text = "None")
    }
}

@Composable
private fun ShowContent(state: State.Success, navController: NavController, modifier: Modifier = Modifier){
    val characters = state.characters
    LazyColumn {
        items(
            items = characters,
            key = {character -> character.id}
        ) {character ->
            Item(
                characterItemUI = character,
                modifier = modifier.clickable {
                    navController.navigate(route = AppScreens.Detail.name + "?${Constant.ID_ARGUMENT}=${character.id}")
                }
            )
        }
    }
}

@Composable
private fun ShowContent(state: State.Loading, navController: NavController, modifier: Modifier = Modifier){
    val characters = state.characters
    if (characters != null){
        LazyColumn {
            items(
                items = characters,
                key = {character -> character.id}
            ) {character ->
                Item(
                    characterItemUI = character,
                    modifier = modifier.clickable {
                        navController.navigate(route = AppScreens.Detail.name + "?${Constant.ID_ARGUMENT}=${character.id}")
                    }
                )
            }
        }
    }else{
        Text(text = "Loading")
    }
}

@Composable
private fun ShowContent(state: State.Error, navController: NavController, modifier: Modifier = Modifier){
    val characters = state.characters
    if (characters != null){
        LazyColumn {
            items(
                items = characters,
                key = {character -> character.id}
            ) {character ->
                Item(
                    characterItemUI = character,
                    modifier = modifier.clickable {
                        navController.navigate(route = AppScreens.Detail.name + "?${Constant.ID_ARGUMENT}=${character.id}")
                    }
                )
            }
        }
    }else{
        Text(text = "Error")
    }
}
@Composable
private fun Item(characterItemUI: CharacterItemUI, modifier: Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = characterItemUI.image,
            contentDescription = "Character Image"
        )
        Text(text = characterItemUI.name)
    }
}

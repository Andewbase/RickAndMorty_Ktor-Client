package com.example.rickandmorty.screen.characters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
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
    mainState: MainState,
    send: (MainEvent) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
){
    when (mainState){
        is MainState.Success -> ShowContent(mainState = mainState, send = send, navController = navController, modifier = modifier)
        is MainState.Loading -> ShowContent(mainState = mainState, send = send, navController = navController, modifier = modifier)
        is MainState.Error -> ShowContent(mainState = mainState, send = send, navController = navController, modifier = modifier)
        MainState.None -> Text(text = "None")
    }
}

@Composable
private fun ShowContent(mainState: MainState.Success, send: (MainEvent) -> Unit, navController: NavController, modifier: Modifier = Modifier){
    val characters = mainState.characters
    LazyColumn {
        items(
            items = characters,
            key = {character -> character.id}
        ) {character ->
            Item(
                characterItemUI = character,
                send = send,
                modifier = modifier.clickable {
                    navController.navigate(route = AppScreens.Detail.name + "?${Constant.ID_ARGUMENT}=${character.id}")
                }
            )
        }
    }
}

@Composable
private fun ShowContent(mainState: MainState.Loading, send: (MainEvent) -> Unit, navController: NavController, modifier: Modifier = Modifier){
    val characters = mainState.characters
    if (characters != null){
        LazyColumn {
            items(
                items = characters,
                key = {character -> character.id}
            ) {character ->
                Item(
                    characterItemUI = character,
                    send = send,
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
private fun ShowContent(mainState: MainState.Error, send: (MainEvent) -> Unit, navController: NavController, modifier: Modifier = Modifier){
    val characters = mainState.characters
    if (characters != null){
        LazyColumn {
            items(
                items = characters,
                key = {character -> character.id}
            ) {character ->

                Item(
                    characterItemUI = character,
                    send = send,
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
private fun Item(
    characterItemUI: CharacterItemUI,
    send: (MainEvent) -> Unit,
    modifier: Modifier = Modifier
){

        Row(modifier = modifier.fillMaxSize()) {
            Box{
                AsyncImage(
                    model = characterItemUI.image,
                    contentDescription = "Character Image"
                )
            }
            Box(modifier = modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
                contentAlignment = Alignment.Center){
                Text(text = characterItemUI.name)
            }

            Box {
              Icon(
                  imageVector = if (characterItemUI.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                  contentDescription = "Favorite",
                  modifier = modifier.clickable {
                      if (!characterItemUI.isFavorite) send(MainEvent.UpdateFavorite( isFavorite = true))
                      else send(MainEvent.UpdateFavorite(isFavorite = false))
                  }
              )
            }
        }



}

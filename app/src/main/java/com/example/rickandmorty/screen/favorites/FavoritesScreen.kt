package com.example.rickandmorty.screen.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import com.example.rickandmorty.domain.Favorite
import com.example.rickandmorty.navigation.AppScreens
import com.example.rickandmorty.screen.characters.CharacterItemUI
import com.example.rickandmorty.screen.characters.MainState

@Composable
fun FavoritesScreen(favorites: List<Favorite>){


    ShowContent(favorites = favorites)



}

@Composable
private fun ShowContent(favorites: List<Favorite>, modifier: Modifier = Modifier){

    if (favorites.isEmpty()){
        Text(text = "List is Empty")
    }else{
        LazyColumn {
            items(
                items = favorites,
                key = { favorite -> favorite.id }
            ) { favorite ->

                Item(
                    favorite = favorite,
                    modifier = modifier
                )
            }
        }
    }

}

@Composable
private fun Item(
    favorite: Favorite,
    modifier: Modifier = Modifier
){

    Row(modifier = modifier.fillMaxSize()) {
        Box{
            AsyncImage(
                model = favorite.image,
                contentDescription = "Character Image"
            )
        }
        Box(modifier = modifier
            .weight(1f)
            .align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center){
            Text(text = favorite.name)
        }
    }



}
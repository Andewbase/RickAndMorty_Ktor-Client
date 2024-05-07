package com.example.rickandmorty.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.rickandmorty.Constant.ID_ARGUMENT
import com.example.rickandmorty.screen.characters.MainScreen
import com.example.rickandmorty.screen.characters.MainViewModel
import com.example.rickandmorty.screen.detail.DetailScreen
import com.example.rickandmorty.screen.detail.DetailViewModel
import com.example.rickandmorty.screen.favorites.FavoritesScreen

@Composable
fun AppNavigation(navController: NavHostController, padding: PaddingValues){



    NavHost(
        navController = navController,
        startDestination = AppScreens.Main.name
    ){
        composable(route = AppScreens.Main.name){
            val mainViewModel = hiltViewModel<MainViewModel>()
            val state by mainViewModel.mainState.collectAsState()
            MainScreen(mainState = state, navController)
        }
        composable(route = detailRoute(), arguments = detailArgument()){
            val detailViewModel = hiltViewModel<DetailViewModel>()
            val state  = detailViewModel.state
            val send = detailViewModel::send
            DetailScreen(state = state, send = send, navController = navController)
        }
        composable(route = AppScreens.Favorites.name){
            FavoritesScreen()
        }
    }
}

private fun detailRoute() = AppScreens.Detail.name + "?${ID_ARGUMENT}={${ID_ARGUMENT}}"

private fun detailArgument(): List<NamedNavArgument>{
    return listOf(
        navArgument(ID_ARGUMENT){
            type = NavType.IntType
            defaultValue = 1
        }
    )
}
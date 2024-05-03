package com.example.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmorty.Constant.ID_ARGUMENT
import com.example.rickandmorty.screen.characters.MainScreen
import com.example.rickandmorty.screen.characters.MainViewModel
import com.example.rickandmorty.screen.detail.DetailScreen
import com.example.rickandmorty.screen.detail.DetailViewModel

@Composable
fun AppNavigation(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.Main.name
    ){
        composable(route = AppScreens.Main.name){
            val mainViewModel = hiltViewModel<MainViewModel>()
            val state by mainViewModel.state.collectAsState()
            MainScreen(state = state, navController)
        }
        composable(route = detailRoute(), arguments = detailArgument()){
            val detailViewModel = hiltViewModel<DetailViewModel>()
            val state  = detailViewModel.state
            DetailScreen(state = state, navController = navController)
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
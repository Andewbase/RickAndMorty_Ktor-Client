package com.example.rickandmorty.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home

object BottomConstants {
    val bottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = AppScreens.Main.name
        ),
        BottomNavItem(
            label = "Favorites",
            icon = Icons.Filled.Favorite,
            route = AppScreens.Favorites.name
        )
    )
}
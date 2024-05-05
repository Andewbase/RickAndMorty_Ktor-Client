package com.example.rickandmorty.navigation

import androidx.annotation.StringRes
import com.example.rickandmorty.R

enum class AppScreens(@StringRes val title: Int) {
    Main(R.string.app_name),
    Detail(R.string.detail_name),
    Favorites(R.string.favorites_name)
}
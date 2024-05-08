package com.example.rickandmorty.screen.favorites

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository.Base
): ViewModel() {



    val favorites = favoriteRepository.getAllFavorites()




}
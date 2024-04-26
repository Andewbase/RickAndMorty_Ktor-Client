package com.example.rickandmorty.screen.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.Constant.ID_ARGUMENT
import com.example.rickandmorty.data.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: RickAndMortyRepository.Base,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var state: DetailState by mutableStateOf(DetailState())
    private set

    init {

        val id: Int = savedStateHandle[ID_ARGUMENT] ?: 1

        viewModelScope.launch {
            repository.getByIdCharacter(id).collect{ character ->
                state = state.copy(character = character)
            }
        }
    }


}
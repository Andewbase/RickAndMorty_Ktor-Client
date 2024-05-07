package com.example.rickandmorty.screen.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.Constant.ID_ARGUMENT
import com.example.rickandmorty.data.RickAndMortyRepository
import com.example.rickandmorty.domain.Character
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

    fun send(event: DetailEvent){
        when(event){
            is DetailEvent.UpdateFavorite ->{
                setCharacterIsFavorite(event.isFavorite)
                updateCharacter(character = state.character)
            }
        }
    }

    private fun updateCharacter(character: Character){
        viewModelScope.launch {
            repository.updateCharacter(character)
        }
    }

    private fun setCharacterIsFavorite(isFavorite: Boolean){
        with(state.character){
            state = state.copy(
                character = Character(
                    created = created,
                    episode = episode,
                    gender = gender,
                    id = id,
                    image = image,
                    location = location,
                    name = name,
                    origin = origin,
                    species = species,
                    status = status,
                    type = type,
                    url = url,
                    isFavorite = isFavorite
                )
            )
        }
    }


}
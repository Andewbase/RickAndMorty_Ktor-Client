package com.example.rickandmorty.screen.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.Constant.ID_ARGUMENT
import com.example.rickandmorty.data.FavoriteRepository
import com.example.rickandmorty.data.RickAndMortyRepository
import com.example.rickandmorty.data.toFavorite
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.Favorite
import com.example.rickandmorty.domain.Location
import com.example.rickandmorty.domain.Origin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository.Base,
    private val favoriteRepository: FavoriteRepository.Base,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var state: DetailState by mutableStateOf(DetailState())
    private set

    init {

        val id: Int = savedStateHandle[ID_ARGUMENT] ?: 1

        viewModelScope.launch {
            rickAndMortyRepository.getByIdCharacter(id).collect{ character ->
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
            rickAndMortyRepository.updateCharacter(character)
            if (character.isFavorite) favoriteRepository.saveFavorite(character.toFavorite())
            else favoriteRepository.deleteFavorite(character.toFavorite())
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

private fun Character.toFavorite(): Favorite{
    return Favorite(
        created = this.created,
        episode = this.episode,
        gender = this.gender,
        id = this.id,
        image = this.image,
        location = Location(
            name = this.location.name,
            url = this.location.url
        ),
        name = this.name,
        origin = Origin(
            name = this.origin.name,
            url = this.origin.url
        ),
        species = this.species,
        status = this.status,
        type = this.type,
        url = this.url,
        isFavorite = this.isFavorite
    )
}
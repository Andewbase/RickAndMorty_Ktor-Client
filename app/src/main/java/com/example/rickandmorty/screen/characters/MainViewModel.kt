package com.example.rickandmorty.screen.characters

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.RequestResult
import com.example.rickandmorty.data.RickAndMortyRepository
import com.example.rickandmorty.data.map
import com.example.rickandmorty.domain.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private  val repository: RickAndMortyRepository.Base): ViewModel() {

    private  val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    val mainState: StateFlow<MainState> = repository.getAll()
        .map { requestResult ->
            requestResult.map { list ->
                list.map {
                    it.toCharacterUI()
                }
            }
        }
        .map {it.toState()}
        .stateIn(viewModelScope, SharingStarted.Lazily, MainState.None)

    fun forceUpdate(){
      val requestResultFlow = repository.fetchLatest()
    }

}

private fun Character.toCharacterUI(): CharacterItemUI {
    return CharacterItemUI(
        id = this.id,
        name = this.name,
        image = this.image,
        isFavorite = this.isFavorite
    )
}

private fun RequestResult<List<CharacterItemUI>>.toState(): MainState {
    return when(this){
        is RequestResult.Success -> MainState.Success(data)
        is RequestResult.Error -> MainState.Error()
        is RequestResult.InProgress -> MainState.Loading(data)
    }
}


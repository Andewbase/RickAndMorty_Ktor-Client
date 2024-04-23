package com.example.rickandmorty.screen

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


    val state: StateFlow<State> = repository.getAll()
        .map { requestResult ->
            requestResult.map { list ->
                list.map {
                    it.toCharacterUI()
                }
            }
        }
        .map {it.toState()}
        .stateIn(viewModelScope, SharingStarted.Lazily, State.None)

    fun forceUpdate(){
      val requestResultFlow = repository.fetchLatest()
    }


}

private fun Character.toCharacterUI(): CharacterUI{
    return CharacterUI(
        id = this.id,
        name = this.name,
        image = this.image
    )
}

private fun RequestResult<List<CharacterUI>>.toState(): State{
    return when(this){
        is RequestResult.Success -> State.Success(data)
        is RequestResult.Error -> State.Error()
        is RequestResult.InProgress -> State.Loading(data)
    }
}

sealed class State {
    object None: State()
    class Loading(val characters: List<CharacterUI>? = null): State()
    class Error(val characters: List<CharacterUI>? = null) : State()
    class Success(val characters: List<CharacterUI>): State()

}
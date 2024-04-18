package com.example.rickandmorty.screen

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private  val repository: RickAndMortyRepository.Base): ViewModel() {

    private  val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    init {
        viewModelScope.launch {
            repository.getAll()
        }
    }

}
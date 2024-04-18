package com.example.rickandmorty.data

import android.util.Log
import com.example.rickandmorty.data.network.RickAndMortyApi
import javax.inject.Inject
import javax.inject.Singleton

interface RickAndMortyRepository {

    suspend fun getAll()
    @Singleton
    class Base @Inject constructor(private val api: RickAndMortyApi): RickAndMortyRepository{
        override suspend fun getAll() {
            when (val apiResult = api.getCharacter(1)){
                is RequestResult.Success -> Log.e("Log-Error", "SUCCES : ${apiResult.data}")
                is RequestResult.Error -> Log.e("Log-Error", "ERROR: ${apiResult.data}")
                is RequestResult.InProgress -> Log.e("Log-Error", "InProgress: ${apiResult.data}")
            }
        }

    }

}
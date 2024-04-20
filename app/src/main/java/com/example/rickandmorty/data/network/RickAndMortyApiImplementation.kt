package com.example.rickandmorty.data.network

import android.util.Log
import com.example.rickandmorty.BuildConfig
import com.example.rickandmorty.data.RequestResult
import com.example.rickandmorty.data.network.entity.CharacterDTO
import com.example.rickandmorty.data.network.entity.GetCharactersDTO
import com.example.rickandmorty.domain.Character
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.url
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RickAndMortyApiImplementation @Inject constructor(private val client: HttpClient):
    RickAndMortyApi {
    override suspend fun getCharacter(page: Int): RequestResult<List<Character>> {
       val response = client.safeRequest<GetCharactersDTO> {
           url( BuildConfig.RICK_AND_MORTY_BASE_URL+"character/")
           parameter("page", page)
       }

        return when(response){
            is HttpClientResult.Success ->{
                Log.e("Log-Error", "SUCCES : ${response.data}")
                RequestResult.Success(data = response.data.characters) // map toCharacter()
            }
            is HttpClientResult.Error ->{
                Log.e("Log-Error", "Error : ${response.exception}")
                RequestResult.Error(error = response.exception.cause)
            }
        }

    }

    private suspend inline fun <reified T> HttpClient.safeRequest(block: HttpRequestBuilder.() -> Unit): HttpClientResult<T> = try {
        val response = request { block() }
        HttpClientResult.Success(response.body())
    } catch (exception: Exception) {
        HttpClientResult.Error(exception)
    }
}
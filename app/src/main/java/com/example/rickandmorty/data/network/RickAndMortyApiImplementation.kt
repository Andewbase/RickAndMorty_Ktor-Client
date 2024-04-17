package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.network.entity.GetCharactersDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RickAndMortyApiImplementation @Inject constructor(private val client: HttpClient):
    RickAndMortyApi {
    override suspend fun getCharacter(page: Int): HttpClientResult<GetCharactersDTO> {
        return safeApiCall {
             client
                 .get("character/?page=$page")
                 .body<GetCharactersDTO>()
        }

    }


    private inline fun <T> safeApiCall(apiCall: () -> T): HttpClientResult<T> {
        return try {
            HttpClientResult.Success(data = apiCall())
        }catch (e: Exception){
            HttpClientResult.Error(exception = e)
        }
    }
}
package com.example.rickandmorty

import com.example.rickandmorty.data.network.HttpClientResult
import com.example.rickandmorty.data.network.RickAndMortyApi
import com.example.rickandmorty.data.network.RickAndMortyApiImplementation
import com.example.rickandmorty.data.network.entity.CharacterDTO
import com.example.rickandmorty.data.network.entity.LocationDTO
import com.example.rickandmorty.data.network.entity.OriginDTO
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.defaultRequest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkTest {

    @Test
    fun test() = runBlocking {
        val client = HttpClient(Android){
            defaultRequest { url(BuildConfig.RICK_AND_MORTY_BASE_URL) }
        }
        val api = RickAndMortyApiImplementation(client)

        val result = api.getCharacter(1)

        var actual: CharacterDTO

        when(result){
            is HttpClientResult.Success -> actual = result.data.characterDTO
            is HttpClientResult.Error -> actual = characterDTO
        }

        val expected = emptyList<CharacterDTO>()
        assertEquals(expected, actual)
    }

    private val characterDTO = CharacterDTO(
        created = "",
        episode = emptyList(),
        gender = "",
        id = 1,
        image = "",
        locationDTO = LocationDTO(
            name = "",
            url = ""
        ),
        name = "",
        originDTO = OriginDTO(
            name = "",
            url = ""
        ),
        species = "",
        status = "",
        type = "",
        url = ""
    )

}
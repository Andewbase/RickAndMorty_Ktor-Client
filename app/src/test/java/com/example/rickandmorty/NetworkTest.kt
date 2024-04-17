package com.example.rickandmorty

import com.example.rickandmorty.data.RequestResult
import com.example.rickandmorty.data.network.HttpClientResult
import com.example.rickandmorty.data.network.RickAndMortyApiImplementation
import com.example.rickandmorty.data.network.entity.CharacterDTO
import com.example.rickandmorty.data.network.entity.LocationDTO
import com.example.rickandmorty.data.network.entity.OriginDTO
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.Location
import com.example.rickandmorty.domain.Origin
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

        var actual: List<Character>

        when(result){
            is RequestResult.Success -> actual = result.data!!
            is RequestResult.Error -> actual = charactersDTO
            is RequestResult.InProgress -> actual = charactersDTO
        }

        val expected = emptyList<CharacterDTO>()
        assertEquals(expected, actual)
    }

    private val charactersDTO = listOf(
        Character(
            created = "",
            episode = emptyList(),
            gender = "",
            id = 1,
            image = "",
            locationDTO = Location(
                name = "",
                url = ""
            ),
            name = "",
            originDTO = Origin(
                name = "",
                url = ""
            ),
            species = "",
            status = "",
            type = "",
            url = ""
        )
    )

}
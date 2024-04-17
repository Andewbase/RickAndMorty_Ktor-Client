package com.example.rickandmorty.data.network.di

import com.example.rickandmorty.BuildConfig
import com.example.rickandmorty.data.network.RickAndMortyApi
import com.example.rickandmorty.data.network.RickAndMortyApiImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRickAndMortyApi(httpClient: HttpClient): RickAndMortyApi {
        return RickAndMortyApiImplementation(httpClient)
    }


    @Provides
    @Singleton
    fun provideClient(): HttpClient{
        val client = HttpClient(Android){
            defaultRequest { url(BuildConfig.RICK_AND_MORTY_BASE_URL) }

            expectSuccess = true

            install(ContentNegotiation){
                    Json{
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
            }
            install(HttpTimeout){
                val timeoutDuration: Long = 30_000
                requestTimeoutMillis = timeoutDuration
                connectTimeoutMillis = timeoutDuration
                socketTimeoutMillis = timeoutDuration
            }
            Logging{
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
            /*HttpResponseValidator {
                handleResponseExceptionWithRequest{ cause, _ ->

                }
            }*/
        }

        return client
    }

}
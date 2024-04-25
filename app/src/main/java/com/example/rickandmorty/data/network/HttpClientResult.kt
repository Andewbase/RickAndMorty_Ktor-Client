package com.example.rickandmorty.data.network

sealed class HttpClientResult<T>(open val data: T? = null, open val exception: Exception? = null) {
    class Success<T>(override val data: T) : HttpClientResult<T>(data = data)
    class Error<T>(override val exception: Exception) : HttpClientResult<T>(exception = exception)
}
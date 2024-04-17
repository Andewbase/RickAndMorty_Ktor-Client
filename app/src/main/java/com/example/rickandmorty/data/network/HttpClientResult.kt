package com.example.rickandmorty.data.network

import java.lang.Exception

sealed class HttpClientResult<T> {

    class Success<T>(val data: T) : HttpClientResult<T>()
    class Error<T>(val exception: Exception) : HttpClientResult<T>()

    fun onSuccess(block: (T) -> Unit): HttpClientResult<T> {
        if (this is Success) block(data)
        return this
    }
}
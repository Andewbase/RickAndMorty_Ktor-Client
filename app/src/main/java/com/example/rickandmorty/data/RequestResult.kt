package com.example.rickandmorty.data

sealed class RequestResult<out E: Any>(open val data: E? = null, open val error: Throwable? = null){

    class InProgress<E: Any>(data: E? = null): RequestResult<E>(data)

    class Success<E: Any>(data: E): RequestResult<E>(data)

    class Error<E: Any>(data: E? = null, error: Throwable? = null): RequestResult<E>(data, error = error)
}


fun <I:Any, O:Any> RequestResult<I>.map(mapper: (I) -> O): RequestResult<O>{
    return when (this){
        is RequestResult.Success -> RequestResult.Success(mapper(data!!))
        is RequestResult.Error -> RequestResult.Error(data?.let(mapper))
        is RequestResult.InProgress -> RequestResult.InProgress(data?.let(mapper))
    }
}

internal fun <T: Any> Result<T>.toRequestResult(): RequestResult<T>{
    return when{
        isSuccess -> RequestResult.Success(getOrThrow())
        isFailure -> RequestResult.Error()
        else -> error("Imposoble Branch")
    }
}
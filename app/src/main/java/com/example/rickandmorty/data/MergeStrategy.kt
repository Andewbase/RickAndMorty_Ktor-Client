package com.example.rickandmorty.data

interface MergeStrategy<T>{

    fun merge(network: T, dataBase: T): T

}

class ResultMergeStrategy<T: Any>: MergeStrategy<RequestResult<T>> {

    override fun merge(network: RequestResult<T>, dataBase: RequestResult<T>): RequestResult<T> {
        return when {
            network is RequestResult.InProgress && dataBase is RequestResult.InProgress -> merge(
                network,
                dataBase
            )

            network is RequestResult.Success && dataBase is RequestResult.InProgress -> merge(
                network,
                dataBase
            )

            network is RequestResult.InProgress && dataBase is RequestResult.Success -> merge(
                network,
                dataBase
            )

            network is RequestResult.Error && dataBase is RequestResult.Success -> merge(
                network,
                dataBase
            )

            network is RequestResult.Success && dataBase is RequestResult.Success -> merge(
                network,
                dataBase
            )

            network is RequestResult.Error && dataBase is RequestResult.InProgress -> merge(
                network,
                dataBase
            )

            network is RequestResult.InProgress && dataBase is RequestResult.Error -> merge(
                network,
                dataBase
            )

            network is RequestResult.Success && dataBase is RequestResult.Error -> merge(
                network,
                dataBase
            )

            else -> error("Error Merge")
        }
    }

    private fun merge (network: RequestResult.InProgress<T>, dataBase: RequestResult.InProgress<T>): RequestResult<T>{
        return when{
            network.data != null -> RequestResult.InProgress(network.data)
            else -> RequestResult.InProgress(dataBase.data)
        }
    }

    private fun merge (network: RequestResult.Success<T>, dataBase: RequestResult.InProgress<T>): RequestResult<T>{
        return RequestResult.InProgress(network.data)
    }

    private fun merge (network: RequestResult.InProgress<T>, dataBase: RequestResult.Success<T>): RequestResult<T>{
        return RequestResult.InProgress(dataBase.data)
    }

    private fun merge (network: RequestResult.Error<T>, dataBase: RequestResult.Success<T>): RequestResult<T>{
        return RequestResult.Error(data = dataBase.data, error = network.error)
    }

    private fun merge (network: RequestResult.Success<T>, dataBase: RequestResult.Success<T>): RequestResult<T>{
        return RequestResult.Success(data = network.data!!)
    }

    private fun merge (network: RequestResult.Error<T>, dataBase: RequestResult.InProgress<T>): RequestResult<T>{
        return RequestResult.Error(data = network.data ?: dataBase.data, error = network.error)
    }

    private fun merge (network: RequestResult.InProgress<T>, dataBase: RequestResult.Error<T>): RequestResult<T>{
        return network
    }

    private fun merge (network: RequestResult.Success<T>, dataBase: RequestResult.Error<T>): RequestResult<T>{
        return network
    }
}
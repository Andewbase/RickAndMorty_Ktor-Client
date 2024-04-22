package com.example.rickandmorty.data

import com.example.rickandmorty.data.cache.CharacterDao
import com.example.rickandmorty.data.network.RickAndMortyApi
import com.example.rickandmorty.domain.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

interface RickAndMortyRepository {

    fun getAll(
        mergeStrategy: MergeStrategy<RequestResult<List<Character>>> = ResultMergeStrategy()
    ): Flow<RequestResult<List<Character>>>

    @Singleton
    class Base @Inject constructor(
        private val api: RickAndMortyApi,
        private val dao: CharacterDao
    ) : RickAndMortyRepository {
        override fun getAll(
            mergeStrategy: MergeStrategy<RequestResult<List<Character>>>
        ): Flow<RequestResult<List<Character>>> {
            val dataBase = getAllDataBase()
            val network = getAllNetwork()

            return dataBase.combine(network, mergeStrategy::merge)
                .flatMapLatest { result ->
                    if (result is RequestResult.Success){
                        dao.getAllCharacters().map { list ->
                            list.map {character ->
                                character.toCharacter()
                            }
                        }
                            .map { RequestResult.Success(it) }
                    }else{
                        flowOf(result)
                    }
                }
        }

        private fun getAllDataBase(): Flow<RequestResult<List<Character>>> {

            val dataBaseRequest = dao::getAllCharacters.asFlow()
                .map {
                    RequestResult.Success(
                        it.map { list ->
                            list.map { character ->
                                character.toCharacter()
                            }
                        }
                    )
                }

            val start = flowOf<RequestResult<List<Character>>>(RequestResult.InProgress())

            return merge(start, dataBaseRequest)
        }

        private fun getAllNetwork(): Flow<RequestResult<List<Character>>> {
           val apiRequest = flow {emit(api.getCharacter(1))}
                .onEach { result ->
                    if (result is RequestResult.Success){
                        dao.saveCharacters(checkNotNull(result.data.map { it.toCharacterDBO() }))
                    }
                }

           val start = flowOf<RequestResult<List<Character>>>(RequestResult.InProgress())

            return merge(apiRequest, start)
        }


    }
}





package com.example.rickandmorty.data

import com.example.rickandmorty.data.cache.CharacterDao
import com.example.rickandmorty.data.cache.entity.CharacterDBO
import com.example.rickandmorty.data.network.RickAndMortyApi
import com.example.rickandmorty.domain.Character
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    fun fetchLatest(): Flow<RequestResult<List<Character>>>

    fun getByIdCharacter(id: Int): Flow<Character>

    suspend fun updateCharacter(character: Character)

    @Singleton
    class Base @Inject constructor(
        private val api: RickAndMortyApi,
        private val dao: CharacterDao
    ) : RickAndMortyRepository {
        @OptIn(ExperimentalCoroutinesApi::class)
        override fun getAll(
            mergeStrategy: MergeStrategy<RequestResult<List<Character>>>
        ): Flow<RequestResult<List<Character>>> {
            val dataBase = getAllDataBase()
            val network = getAllNetwork()

            return dataBase.combine(network, mergeStrategy::merge)
                .flatMapLatest { result ->
                    if (result is RequestResult.Success){
                        dao.observeAll().map { list ->
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

        override fun fetchLatest(): Flow<RequestResult<List<Character>>>{
            return getAllNetwork()
        }

        override fun getByIdCharacter(id: Int): Flow<Character> {
            return dao.getCharacterById(id).map{it.toCharacter()}
        }

        override suspend fun updateCharacter(character: Character) {
            dao.updateCharacter(character.toCharacterDBO())
        }

        private fun getAllDataBase(): Flow<RequestResult<List<Character>>> {

            val dataBaseRequest = dao::getAllCharacters.asFlow()
                .map {
                    RequestResult.Success(it)
                }

            val start = flowOf<RequestResult<List<CharacterDBO>>>(RequestResult.InProgress())

            return merge(start, dataBaseRequest).map { result ->
               result.map { list ->
                   list.map { character ->
                       character.toCharacter()
                   }
               }
            }
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





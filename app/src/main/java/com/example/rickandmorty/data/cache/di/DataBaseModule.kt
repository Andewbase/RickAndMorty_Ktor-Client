package com.example.rickandmorty.data.cache.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.data.cache.CharacterDao
import com.example.rickandmorty.data.cache.RickAndMortyDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideRickAndMortyDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            RickAndMortyDataBase::class.java,
            "rick_and_morty_database"
        ).build()

    @Provides
    @Singleton
    fun provideCharacterDao(appDatabase: RickAndMortyDataBase): CharacterDao{
        return appDatabase.getCharacterDao()
    }

}
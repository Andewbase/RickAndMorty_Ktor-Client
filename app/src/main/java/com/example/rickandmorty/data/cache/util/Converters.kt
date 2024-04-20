package com.example.rickandmorty.data.cache.util

import androidx.room.TypeConverter

internal class Converters{
    @TypeConverter
    fun convertListToString(episode: List<String>): String{
        return episode.joinToString()
    }

    @TypeConverter
    fun convertStringToList(episodeString: String): List<String>{
        return episodeString.split(",")
    }
}

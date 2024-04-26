package com.example.rickandmorty.screen.detail

import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.Location
import com.example.rickandmorty.domain.Origin

data class DetailState(
    val character: Character =
        Character(
            "",
            emptyList(),
            "",
            0,
            "",
            Location(
                "",
                ""
            ),
            "",
            Origin(
                "",
                ""
            ),
            "",
            "",
            "",
            ""
        )
)

package com.example.rickandmorty.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmorty.ui.theme.RickAndMortyTheme

@Composable
fun DetailScreen (state: DetailState, modifier: Modifier = Modifier){

    val character = state.character

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {

        AsyncImage(
            modifier = modifier.align(Alignment.CenterHorizontally),
            model = character.image,
            contentDescription = "Character Img"
        )

        Row(modifier = modifier.fillMaxWidth()){
            Box(modifier.padding(start = 20.dp)) {
                Text(text = "Name :")
            }
            Box(
                modifier = modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(end = 100.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = character.name)
            }
        }

        Row(modifier = modifier.fillMaxWidth()){
            Box(modifier.padding(start = 20.dp)) {
                Text(text = "Gender :")
            }
            Box(
                modifier = modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(end = 100.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(text =character.gender)
            }
        }

        Row(modifier = modifier.fillMaxWidth()){
            Box(modifier.padding(start = 20.dp)) {
                Text(text = "Status :")
            }
            Box(
                modifier = modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(end = 100.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(text =character.status)
            }
        }

        Row(modifier = modifier.fillMaxWidth()){
            Box(modifier.padding(start = 20.dp)) {
                Text(text = "Type :")
            }
            Box(
                modifier = modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(end = 100.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = character.type)
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {

    RickAndMortyTheme {
        DetailScreen(
            state = DetailState()
        )
    }
}

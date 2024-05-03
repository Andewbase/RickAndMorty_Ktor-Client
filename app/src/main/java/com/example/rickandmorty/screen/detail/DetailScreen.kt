package com.example.rickandmorty.screen.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.rickandmorty.ui.theme.RickAndMortyTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen (
    state: DetailState,
    navController: NavController,
    modifier: Modifier = Modifier
){

    val character = state.character

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Small Top App Bar")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
    ){
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
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



}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {

    RickAndMortyTheme {
        DetailScreen(
            state = DetailState(),
            navController = rememberNavController()
        )
    }
}

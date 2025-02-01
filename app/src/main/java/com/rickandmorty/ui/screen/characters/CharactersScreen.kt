package com.rickandmorty.ui.screen.characters
import com.rickandmorty.data.models.Character

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.rickandmorty.data.remote.response.CharacterResponse
import com.rickandmorty.ui.nav.navRoute.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharactersScreen(
    navController: NavHostController,
    charactersViewModel: CharactersViewModel = koinViewModel<CharactersViewModel>()
) {
    val characters = charactersViewModel.charactersFlow.collectAsState()

    LaunchedEffect(Unit) {
        charactersViewModel.getCharacters()
    }


    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ){
            items(
                items = characters.value ?: emptyList(),
            ) { character ->
                CharacterCard(
                    character = character,
                ) {
                    navController.navigate(
                        Screen.CharacterDetail(
                            character.id
                        ))
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterCard(character: CharacterResponse, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .clickable{
                onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .padding(end = 24.dp)
                .fillMaxWidth()
        ) {
            GlideImage(
                model = character.image,
                contentDescription = "image",
                modifier = Modifier
                    .clickable {
                        onClick()
                    }
            )

            Column (
                modifier = Modifier
                    .padding(start = 16.dp, top = 12.dp)
            ) {
                Text(
                    fontSize = 28.sp,
                    text = character.name
                )
                Text(
                    text = character.status,
                    modifier = Modifier
                        .padding(top = 4.dp)
                )
            }
        }

    }
}
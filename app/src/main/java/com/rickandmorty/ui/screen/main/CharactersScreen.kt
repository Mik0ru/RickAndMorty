package com.rickandmorty.ui.screen.main
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.rickandmorty.ui.navRoute.Screen

@Composable
fun CharactersScreen(navController: NavHostController) {
    val characters = listOf(
       Character(
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            "Rick Sanchez",
            "Alive",
            "Human",
            "Male",
            "Earth (C-137)"
        ),
        Character(
            "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            "Morty Smith",
            "Alive",
            "Human",
            "Male",
            "Earth (C-137)"
        ),
        Character(
            "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
            "Summer Smith",
            "Alive",
            "Human",
            "Female",
            "Earth (C-137)"
        ),
        Character(
            "https://rickandmortyapi.com/api/character/avatar/4.jpeg",
            "Beth Smith",
            "Alive",
            "Human",
            "Female",
            "Earth (C-137)"
        ),
        Character(
            "https://rickandmortyapi.com/api/character/avatar/5.jpeg",
            "Jerry Smith",
            "Alive",
            "Human",
            "Male",
            "Earth (C-137)"
        ),

        Character(
            "https://rickandmortyapi.com/api/character/avatar/6.jpeg",
            "Abadango Cluster Princess",
            "Alive",
            "Alien",
            "Female",
            "Abadango"
        ),
        Character(
            "https://rickandmortyapi.com/api/character/avatar/7.jpeg",
            "Abradolf Lincler",
            "Dead",
            "Human",
            "Male",
            "Earth (Replacement Dimension)"
        ),
        Character(
            "https://rickandmortyapi.com/api/character/avatar/8.jpeg",
            "Adjudicator Rick",
            "Alive",
            "Human",
            "Male",
            "Citadel of Ricks"
        )
    )
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
                items = characters
            ) { character ->
                CharacterCard(
                    character = character
                ) {
                    navController.navigate(
                        Screen.CharacterDetail(
                        character.imageUrl,
                        character.name,
                        character.status,
                        character.species,
                        character.gender,
                        character.location
                    ))
                }
            }
        }
    }
}
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterCard(character: Character, onClick: () -> Unit) {
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
                model = character.imageUrl,
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
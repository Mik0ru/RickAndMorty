package com.rickandmorty.ui.screen.characters

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
import com.rickandmorty.ui.composables.CharacterCard
import com.rickandmorty.ui.nav.navRoute.Screen
import com.rickandmorty.ui.screen.characters.favorites.FavoritesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharactersScreen(
    navController: NavHostController,
    charactersViewModel: CharactersViewModel = koinViewModel<CharactersViewModel>(),
    favoritesViewModel: FavoritesViewModel = koinViewModel<FavoritesViewModel>()
) {
    LaunchedEffect(key1 = Unit) {
        charactersViewModel.getCharacters()
        favoritesViewModel.getFavorites()
    }

    val favorites = favoritesViewModel.favoritesFlow.collectAsState()
    val characters = charactersViewModel.charactersFlow.collectAsState()

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
                val isFavorite = favorites.value?.any { it.characterId == character.id } ?: false
                CharacterCard(
                    character = character.toCharacterEntity(),
                    favoritesViewModel = favoritesViewModel,
                    isFavorite = isFavorite,
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
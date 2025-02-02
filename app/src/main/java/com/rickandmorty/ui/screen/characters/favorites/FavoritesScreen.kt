package com.rickandmorty.ui.screen.characters.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rickandmorty.ui.composables.CharacterCard
import com.rickandmorty.ui.nav.navRoute.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesScreen(
    navController: NavHostController
) {

    val favoritesViewModel = koinViewModel<FavoritesViewModel>()
    favoritesViewModel.getFavorites()
    val favorites = favoritesViewModel.favoritesFlow.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        items(
            items = favorites.value ?: emptyList()
        ) { character ->
            CharacterCard(
                character = character,
                favoritesViewModel = favoritesViewModel,
                isFavorite = true,
                onClick = {
                    navController.navigate(
                        Screen.CharacterDetail(
                            character.characterId
                        )
                    )
                }
            )
        }
    }
}
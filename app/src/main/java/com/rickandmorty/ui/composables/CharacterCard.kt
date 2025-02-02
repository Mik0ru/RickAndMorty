package com.rickandmorty.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.rickandmorty.R
import com.rickandmorty.data.local.models.CharacterEntity
import com.rickandmorty.ui.screen.characters.favorites.FavoritesViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterCard(character: CharacterEntity, favoritesViewModel: FavoritesViewModel, isFavorite: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .clickable{
                onClick()
            }

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
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
                    .padding(top = 12.dp, start = 12.dp)
                    .width(216.dp)
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
            if (isFavorite) {
                Image(
                    painter = painterResource(id = R.drawable.favorite),
                    colorFilter = ColorFilter.tint(Color.Red),
                    contentDescription = "favorite",
                    modifier = Modifier
                        .width(28.dp)
                        .padding(top = 12.dp)
                        .clickable {
                            favoritesViewModel.onFavoriteClick(character)
                        }
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.favorite),
                    contentDescription = "favorite",
                    modifier = Modifier
                        .width(28.dp)
                        .padding(top = 12.dp)
                        .clickable {
                            favoritesViewModel.onFavoriteClick(character)
                        }
                )
            }

        }
    }
}
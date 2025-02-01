package com.rickandmorty.ui.screen.characters.detail

import com.rickandmorty.data.models.Character
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.rickandmorty.ui.screen.characters.CharactersViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterDetailScreen(
    id: Int,
    charactersViewModel: CharactersViewModel = koinViewModel<CharactersViewModel>()
) {
    val character = charactersViewModel.characterFlow.collectAsState()

    LaunchedEffect(Unit) {
        charactersViewModel.getCharacterById(id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .width(240.dp)
        ){
            GlideImage(
                model = character.value?.image,
                contentDescription = "image",
                contentScale = androidx.compose.ui.layout.ContentScale.FillWidth
            )
            Text(
                text = "Name: ${character.value?.name}",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                fontSize = 20.sp,
                text = "Status: ${character.value?.status}"
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                fontSize = 20.sp,
                text = "Species: ${character.value?.species}"
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                fontSize = 20.sp,
                text = "Gender: ${character.value?.gender}"
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                fontSize = 20.sp,
                text = "Location: ${character.value?.location?.name}"
            )
        }

    }


}
package com.rickandmorty.ui.screen.detail

import com.rickandmorty.data.models.Character
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterDetailScreen(character: Character) {
    Log.e("lol", "CharacterDetailScreen: ", )
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
                model = character.imageUrl,
                contentDescription = "image",
                contentScale = androidx.compose.ui.layout.ContentScale.FillWidth
            )
            Text(
                text = "Name: ${character.name}",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                fontSize = 20.sp,
                text = "Status: ${character.status}"
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                fontSize = 20.sp,
                text = "Species: ${character.species}"
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                fontSize = 20.sp,
                text = "Gender: ${character.gender}"
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                fontSize = 20.sp,
                text = "Location: ${character.location}"
            )
        }

    }


}
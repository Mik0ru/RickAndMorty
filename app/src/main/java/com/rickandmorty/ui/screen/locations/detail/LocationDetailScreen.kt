package com.rickandmorty.ui.screen.locations.detail

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rickandmorty.ui.screen.locations.LocationsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LocationDetailScreen(
    id: Int,
    locationsViewModel: LocationsViewModel = koinViewModel<LocationsViewModel>()
) {
    val location = locationsViewModel.locationFlow.collectAsState()

    LaunchedEffect(Unit) {
        locationsViewModel.getLocationById(id)
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
            Text(
                text = "Name: ${location.value?.name}",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                fontSize = 20.sp,
                text = "Type: ${location.value?.type}"
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                fontSize = 20.sp,
                text = "Dimension: ${location.value?.dimension}"
            )
        }

    }
}
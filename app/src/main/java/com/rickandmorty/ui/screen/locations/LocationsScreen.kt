package com.rickandmorty.ui.screen.locations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rickandmorty.data.remote.response.LocationResponse
import com.rickandmorty.ui.nav.navRoute.Screen
import org.koin.androidx.compose.koinViewModel


@Composable
fun LocationsScreen(
    navController: NavHostController,
    locationsViewModel: LocationsViewModel = koinViewModel<LocationsViewModel>()
) {
    val locations = locationsViewModel.locationsFlow.collectAsState()

    LaunchedEffect(Unit) {
        locationsViewModel.getLocations()
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
                items = locations.value ?: emptyList(),
            ) { location ->
                LocationCard(
                    location = location,
                ) {
                    navController.navigate(
                        Screen.LocationDetail(
                            location.id
                        ))
                }
            }
        }
    }
}
@Composable
fun LocationCard(location: LocationResponse, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .clickable{
                onClick()
            }
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                fontSize = 28.sp,
                text = location.name
            )
            Text(
                text = location.type,
                modifier = Modifier
                    .padding(top = 4.dp)
            )
        }


    }
}
package com.rickandmorty.ui.screen.locations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.rickandmorty.data.remote.response.LocationResponse
import com.rickandmorty.ui.composables.LoadStateHandler
import com.rickandmorty.ui.nav.navRoute.Screen
import org.koin.androidx.compose.koinViewModel


@Composable
fun LocationsScreen(
    navController: NavHostController,
    locationsViewModel: LocationsViewModel = koinViewModel<LocationsViewModel>()
) {
    val locationPagingData = locationsViewModel.locationsFlow.collectAsLazyPagingItems()
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = locationsViewModel.savedScrollPosition,
        initialFirstVisibleItemScrollOffset = locationsViewModel.savedScrollOffset
    )

    DisposableEffect(Unit) {
        onDispose {
            locationsViewModel.savedScrollPosition = listState.firstVisibleItemIndex
            locationsViewModel.savedScrollOffset = listState.firstVisibleItemScrollOffset
        }
    }
    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize(),
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ){
            items(
                locationPagingData.itemCount,
                key = { index ->
                    locationPagingData[index]?.id ?: index
                }
            ) { index ->
                val location = locationPagingData[index]
                if (location != null) {
                    LocationCard(
                        location = location,
                    ) {
                        navController.navigate(
                            Screen.LocationDetail(
                                location.id
                            )
                        )
                    }
                }
            }
            locationPagingData.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            LoadStateHandler(
                                loadState = loadState.refresh,
                                modifier = Modifier.fillParentMaxSize(),
                                onRetry = { retry() }
                            )
                        }
                    }
                    loadState.append is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(36.dp)
                                    .padding(16.dp)
                            )
                        }
                    }
                    loadState.refresh is LoadState.Error -> {
                        item {
                            LoadStateHandler(
                                loadState = loadState.refresh,
                                modifier = Modifier.fillParentMaxSize(),
                                onRetry = { retry() }
                            )
                        }
                    }
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
package com.rickandmorty.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState

@Composable
fun LoadStateHandler(
    loadState: LoadState,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit
) {
    when (loadState) {
        is LoadState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is LoadState.Error -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "An error occurred: ${loadState.error.message}")
                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                    )
                    Button(onClick = onRetry) {
                        Text(text = "Retry")
                    }
                }
            }
        }

        is LoadState.NotLoading -> {

        }
    }
}
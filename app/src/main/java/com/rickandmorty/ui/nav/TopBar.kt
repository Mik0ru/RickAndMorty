package com.rickandmorty.ui.nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import com.rickandmorty.ui.nav.navRoute.Screen

@Composable
fun TopBar(currentDestination: NavDestination?) {
    val title = when (currentDestination?.route) {
        Screen.Characters::class.qualifiedName -> "Characters"
        Screen.Episodes::class.qualifiedName -> "Episodes"
        Screen.Locations::class.qualifiedName -> "Locations"
        else -> ""
    }


    Column(
        modifier = Modifier
            .background(Color(0xff89aae3))
            .fillMaxWidth()
            .height(80.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 24.dp, top = 32.dp, bottom = 12.dp)
        )
    }
}
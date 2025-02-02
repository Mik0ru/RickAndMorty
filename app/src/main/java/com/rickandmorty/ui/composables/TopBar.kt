package com.rickandmorty.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.rickandmorty.R
import com.rickandmorty.ui.nav.navRoute.Screen

@Composable
fun TopBar(currentDestination: NavDestination?, navController: NavHostController) {
    val title = when (currentDestination?.route) {
        Screen.Characters::class.qualifiedName -> "Characters"
        Screen.Episodes::class.qualifiedName -> "Episodes"
        Screen.Locations::class.qualifiedName -> "Locations"
        else -> ""
    }

    Row(
        modifier = Modifier
            .background(Color(0xff89aae3))
            .fillMaxWidth()
            .height(80.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 24.dp, top = 32.dp, bottom = 12.dp)
        )
        if (currentDestination?.route == Screen.Characters::class.qualifiedName){
            Button(
                content = {
                    Text(
                        text = "Favorites",
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(end = 4.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.favorite),
                        colorFilter = ColorFilter.tint(Color.Red),
                        contentDescription = "favorite",
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                },
                modifier = Modifier
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors( containerColor = Color.Transparent ),
                onClick = {
                    navController.navigate(Screen.Favorites)
                }
            )
        }
    }
}
package com.rickandmorty.ui.nav

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rickandmorty.R
import com.rickandmorty.ui.nav.navRoute.Screen
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomNavItem<T>(val title: String, val icon: Int, val screenRoute: T) {
    data object Characters : BottomNavItem<Screen.Characters>(
        "Characters",
        icon = R.drawable.rick,
        screenRoute = Screen.Characters
    )
    data object Episodes : BottomNavItem<Screen.Episodes>(
        "Episodes",
        icon = R.drawable.episode,
        screenRoute = Screen.Episodes
    )
    data object Locations : BottomNavItem<Screen.Locations>(
        "Locations",
        icon = R.drawable.planet,
        screenRoute = Screen.Locations
    )
}


@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavItem.Characters,
        BottomNavItem.Episodes,
        BottomNavItem.Locations
    )


    NavigationBar(modifier = modifier, containerColor = Color(0xff89aae3)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            val isItemSelected = currentDestination?.route == screen.screenRoute::class.qualifiedName
            NavigationBarItem(
                selected = isItemSelected,
                icon = {
                    Image(
                        painter = painterResource(screen.icon),
                        modifier = Modifier
                            .width(32.dp),
                        contentDescription = screen.title,
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.White)
                    )
                },
                label = {
                    Text(
                        text = screen.title,
                        color = Color.White
                    )
                },
                onClick = {
                    navController.navigate(screen.screenRoute) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
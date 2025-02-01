package com.rickandmorty.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rickandmorty.ui.navRoute.Screen
import com.rickandmorty.ui.screen.detail.CharacterDetailScreen
import com.rickandmorty.ui.screen.detail.EpisodeDetailScreen
import com.rickandmorty.ui.screen.detail.LocationDetailScreen
import com.rickandmorty.ui.screen.main.CharactersScreen
import com.rickandmorty.ui.screen.main.EpisodesScreen
import com.rickandmorty.ui.screen.main.LocationsScreen
import com.rickandmorty.ui.theme.RickAndMortyTheme
import com.rickandmorty.data.models.Character
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MainScreen(navController)
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showTopBarAndBottomBar = currentDestination?.route in listOf(
        Screen.Characters::class.qualifiedName,
        Screen.Episodes::class.qualifiedName,
        Screen.Locations::class.qualifiedName
    )

    Scaffold(
        topBar = {
            if (showTopBarAndBottomBar) TopBar(currentDestination)
        },
        bottomBar = {
            if (showTopBarAndBottomBar) BottomBar(navController)
        }
    ) { paddingValues ->
        Surface(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            NavHostInit(navController)
        }
    }
}

@Composable
fun NavHostInit(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.Characters) {
        composable<Screen.Characters> {
            CharactersScreen(navController)
        }
        composable<Screen.Episodes> {
            EpisodesScreen()
        }
        composable<Screen.Locations> {
            LocationsScreen()
        }
        composable<Screen.LocationDetail> {
            LocationDetailScreen()
        }
        composable<Screen.CharacterDetail> {
            val args = it.toRoute<Screen.CharacterDetail>()
            CharacterDetailScreen(Character(
                args.imageUrl,
                args.name,
                args.status,
                args.species,
                args.gender,
                args.location
            ))
        }
        composable<Screen.EpisodeDetail> {
            EpisodeDetailScreen()
        }

    }
}
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
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 24.dp, top = 32.dp, bottom = 12.dp)
        )
    }
}
@Serializable
sealed class BottomNavItem<T>(val title: String, val screenRoute: T) {
    data object Characters : BottomNavItem<Screen.Characters>(
        "Characters",
        screenRoute = Screen.Characters
    )
    data object Episodes : BottomNavItem<Screen.Episodes>(
        "Episodes",
        screenRoute = Screen.Episodes
    )
    data object Locations : BottomNavItem<Screen.Locations>(
        "Locations",
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
                    Text(text = screen.title)
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
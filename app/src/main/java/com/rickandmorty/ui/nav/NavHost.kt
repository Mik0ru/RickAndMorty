package com.rickandmorty.ui.nav
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.rickandmorty.ui.nav.navRoute.Screen
import com.rickandmorty.ui.screen.characters.detail.CharacterDetailScreen
import com.rickandmorty.ui.screen.episodes.detail.EpisodeDetailScreen
import com.rickandmorty.ui.screen.locations.detail.LocationDetailScreen
import com.rickandmorty.ui.screen.characters.CharactersScreen
import com.rickandmorty.ui.screen.episodes.EpisodesScreen
import com.rickandmorty.ui.screen.locations.LocationsScreen


@Composable
fun NavHostInit(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.Characters) {

        composable<Screen.Characters> {
            CharactersScreen(navController)
        }
        composable<Screen.CharacterDetail> {
            val args = it.toRoute<Screen.CharacterDetail>()
            CharacterDetailScreen(
                args.id
            )
        }

        composable<Screen.Episodes> {
            EpisodesScreen(navController)
        }
        composable<Screen.EpisodeDetail> {
            val args = it.toRoute<Screen.EpisodeDetail>()
            EpisodeDetailScreen(args.id)
        }

        composable<Screen.Locations> {
            LocationsScreen(navController)
        }
        composable<Screen.LocationDetail> {
            val args = it.toRoute<Screen.LocationDetail>()
            LocationDetailScreen(args.id)
        }



    }
}
package com.rickandmorty.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rickandmorty.ui.nav.navRoute.Screen
import com.rickandmorty.ui.nav.BottomBar
import com.rickandmorty.ui.nav.NavHostInit
import com.rickandmorty.ui.nav.TopBar

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
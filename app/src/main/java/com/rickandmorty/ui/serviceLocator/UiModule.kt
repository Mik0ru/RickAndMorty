package com.rickandmorty.ui.serviceLocator

import com.rickandmorty.ui.screen.characters.CharactersViewModel
import com.rickandmorty.ui.screen.characters.favorites.FavoritesViewModel
import com.rickandmorty.ui.screen.episodes.EpisodesViewModel
import com.rickandmorty.ui.screen.locations.LocationsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { CharactersViewModel(get()) }
    viewModel { LocationsViewModel(get()) }
    viewModel { EpisodesViewModel(get()) }
    viewModel { FavoritesViewModel(get()) }


}
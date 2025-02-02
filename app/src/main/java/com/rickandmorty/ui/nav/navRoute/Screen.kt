package com.rickandmorty.ui.nav.navRoute

import kotlinx.serialization.Serializable

object Screen {

    @Serializable
    object Characters

    @Serializable
    object Favorites

    @Serializable
    object Episodes

    @Serializable
    object Locations

    @Serializable
    data class CharacterDetail(
        val id: Int
    )

    @Serializable
    data class EpisodeDetail(
        val id: Int,
    )

    @Serializable
    data class LocationDetail(
        val id: Int
    )
}
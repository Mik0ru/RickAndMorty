package com.rickandmorty.ui.navRoute

import kotlinx.serialization.Serializable

object Screen {

    @Serializable
    object Characters

    @Serializable
    object Episodes

    @Serializable
    object Locations

    @Serializable
    data class CharacterDetail(
        val imageUrl: String,
        val name: String,
        val status: String,
        val species: String,
        val gender: String,
        val location: String
    )

    @Serializable
    data class EpisodeDetail(
        val name: String,
    )

    @Serializable
    data class LocationDetail(
        val name: String
    )
}
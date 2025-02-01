package com.rickandmorty.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val imageUrl: String,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val location: String
)
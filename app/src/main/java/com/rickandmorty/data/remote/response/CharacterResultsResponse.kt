package com.rickandmorty.data.remote.response

import com.google.gson.annotations.SerializedName
import com.rickandmorty.data.local.models.CharacterEntity

data class CharacterResultsResponse(
    @SerializedName("results")
    val results: List<CharacterResponse>
)
data class CharacterResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("location")
    val location: LocationResponse,
    @SerializedName("image")
    val image: String
) {
    fun toCharacterEntity(): CharacterEntity {
        return CharacterEntity(
            characterId = id,
            name = name,
            status = status,
            image = image
        )
    }
}
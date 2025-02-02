package com.rickandmorty.data.remote.response

import com.google.gson.annotations.SerializedName
import com.rickandmorty.data.local.models.CharacterEntity

data class CharacterResultsResponse(
    @SerializedName("info")
    val info: PagingInfo,
    @SerializedName("results")
    val results: List<CharacterResponse>
)

data class PagingInfo(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val nextPage: String = String(),
    @SerializedName("prev")
    val prevPage: String = String()
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
    val location: CharacterResponse,
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
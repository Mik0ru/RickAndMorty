package com.rickandmorty.data.remote.response

import com.google.gson.annotations.SerializedName

data class EpisodeResultsResponse (
    @SerializedName("results")
    val results: List<EpisodeResponse>
)
data class EpisodeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val airDate: String,
    @SerializedName("episode")
    val episode: String,
    @SerializedName("url")
    val url: String,
)

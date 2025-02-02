package com.rickandmorty.data.remote.response

import com.google.gson.annotations.SerializedName

data class LocationResultsResponse(
    @SerializedName("info")
    val info: PagingInfo,

    @SerializedName("results")
    val results: List<LocationResponse>
)
data class LocationResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("dimension")
    val dimension: String
)
package com.rickandmorty.data.remote.api

import com.rickandmorty.data.remote.response.EpisodeResponse
import com.rickandmorty.data.remote.response.EpisodeResultsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApiService {

    @GET("episode")
    suspend fun getAllEpisodes(): Response<EpisodeResultsResponse>

    @GET("episode/{id}")
    suspend fun getEpisodeById(@Path("id") id: Int): Response<EpisodeResponse>

}
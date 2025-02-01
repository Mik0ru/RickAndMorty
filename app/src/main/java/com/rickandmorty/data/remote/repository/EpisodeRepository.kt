package com.rickandmorty.data.remote.repository

import com.rickandmorty.data.remote.api.EpisodeApiService
import com.rickandmorty.data.remote.response.EpisodeResponse

class EpisodeRepository(
    private val episodeApiService: EpisodeApiService
) {
    suspend fun getEpisodes(): List<EpisodeResponse>? {
        val response = episodeApiService.getAllEpisodes()
        return if (response.isSuccessful) {
            response.body()?.results
        } else {
            emptyList()
        }
    }
    suspend fun getEpisodeById(id: Int): EpisodeResponse? {
        val response = episodeApiService.getEpisodeById(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}
package com.rickandmorty.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rickandmorty.data.paging.EpisodesPagingSource
import com.rickandmorty.data.remote.api.EpisodeApiService
import com.rickandmorty.data.remote.response.EpisodeResponse
import kotlinx.coroutines.flow.Flow

class EpisodeRepository(
    private val episodeApiService: EpisodeApiService
) {
    fun getAllEpisodes(): Flow<PagingData<EpisodeResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                initialLoadSize = 60
            ),
            pagingSourceFactory = {
                EpisodesPagingSource(episodeApiService)
            }
        ).flow
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
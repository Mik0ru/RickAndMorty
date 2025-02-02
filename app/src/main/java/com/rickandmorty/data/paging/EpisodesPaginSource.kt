package com.rickandmorty.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rickandmorty.data.remote.api.EpisodeApiService
import com.rickandmorty.data.remote.response.EpisodeResponse

class EpisodesPagingSource(
    private val episodeApiService: EpisodeApiService
) :
    PagingSource<Int, EpisodeResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeResponse> {
        return try {
            val position = params.key ?: 1
            val response = episodeApiService.getAllEpisodes(position)

            LoadResult.Page(
                data = response.body()?.results?: emptyList(),
                prevKey = if (position == 1) null else (position - 1),
                nextKey = if (position == response.body()?.info?.pages) null else (position + 1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, EpisodeResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
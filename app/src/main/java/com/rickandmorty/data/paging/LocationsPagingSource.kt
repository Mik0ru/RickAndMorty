package com.rickandmorty.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rickandmorty.data.remote.api.LocationApiService
import com.rickandmorty.data.remote.response.LocationResponse

class LocationsPagingSource (
    private val locationApiService: LocationApiService
) :
    PagingSource<Int, LocationResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationResponse> {
        return try {
            val position = params.key ?: 1
            val response = locationApiService.getAllLocations(position)

            LoadResult.Page(
                data = response.body()?.results?: emptyList(),
                prevKey = if (position == 1) null else (position - 1),
                nextKey = if (position == response.body()?.info?.pages) null else (position + 1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LocationResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
package com.rickandmorty.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rickandmorty.data.remote.api.CharacterApiService
import com.rickandmorty.data.remote.response.CharacterResponse

class CharactersPagingSource(
    private val characterApiService: CharacterApiService
) :
    PagingSource<Int, CharacterResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> {
        return try {
            val position = params.key ?: 1
            val response = characterApiService.getAllCharacters(position)

            LoadResult.Page(
                data = response.body()?.results?: emptyList(),
                prevKey = if (position == 1) null else (position - 1),
                nextKey = if (position == response.body()?.info?.pages) null else (position + 1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
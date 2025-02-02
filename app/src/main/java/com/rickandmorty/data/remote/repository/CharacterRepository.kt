package com.rickandmorty.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rickandmorty.data.paging.CharactersPagingSource
import com.rickandmorty.data.remote.api.CharacterApiService
import com.rickandmorty.data.remote.response.CharacterResponse
import kotlinx.coroutines.flow.Flow

class CharacterRepository(
    private val characterApiService: CharacterApiService
) {
    suspend fun getCharacterById(id: Int): CharacterResponse? {
        val response = characterApiService.getCharacterById(id)
        return response.body()
    }
    fun getAllCharacters(): Flow<PagingData<CharacterResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                initialLoadSize = 60
            ),
            pagingSourceFactory = {
                CharactersPagingSource(characterApiService)
            }
        ).flow
    }
}
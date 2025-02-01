package com.rickandmorty.data.remote.repository

import com.rickandmorty.data.remote.api.CharacterApiService
import com.rickandmorty.data.remote.response.CharacterResponse

class CharacterRepository(
    private val characterApiService: CharacterApiService
) {
    suspend fun getCharacters(): List<CharacterResponse>? {
        val response = characterApiService.getAllCharacters()
        return if (response.isSuccessful) {
            response.body()?.results
        } else {
            emptyList()
        }
    }

    suspend fun getCharacterById(id: Int): CharacterResponse? {
        val response = characterApiService.getCharacterById(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}
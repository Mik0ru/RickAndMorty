package com.rickandmorty.data.remote.api

import com.rickandmorty.data.remote.response.CharacterResponse
import com.rickandmorty.data.remote.response.CharacterResultsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {
    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): Response<CharacterResultsResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<CharacterResponse>
}
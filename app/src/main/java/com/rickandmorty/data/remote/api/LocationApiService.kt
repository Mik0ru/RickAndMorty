package com.rickandmorty.data.remote.api

import com.rickandmorty.data.remote.response.LocationResponse
import com.rickandmorty.data.remote.response.LocationResultsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationApiService {

    @GET("location")
    suspend fun getAllLocations(): Response<LocationResultsResponse>

    @GET("location/{id}")
    suspend fun getLocationById(@Path("id") id: Int): Response<LocationResponse>

}
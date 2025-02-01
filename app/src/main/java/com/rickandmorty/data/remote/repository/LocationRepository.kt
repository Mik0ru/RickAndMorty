package com.rickandmorty.data.remote.repository

import com.rickandmorty.data.remote.api.LocationApiService
import com.rickandmorty.data.remote.response.LocationResponse

class LocationRepository(
    private val locationApiService: LocationApiService
) {
    suspend fun getLocations(): List<LocationResponse> {
        val response = locationApiService.getAllLocations()
        return if (response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {
            emptyList()
        }
    }
    suspend fun getLocationById(id: Int): LocationResponse? {
        val response = locationApiService.getLocationById(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}
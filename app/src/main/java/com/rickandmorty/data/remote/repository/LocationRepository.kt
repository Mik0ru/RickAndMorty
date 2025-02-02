package com.rickandmorty.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rickandmorty.data.paging.LocationsPagingSource
import com.rickandmorty.data.remote.api.LocationApiService
import com.rickandmorty.data.remote.response.LocationResponse
import kotlinx.coroutines.flow.Flow

class LocationRepository(
    private val locationApiService: LocationApiService
) {
    fun getAllLocations(): Flow<PagingData<LocationResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                initialLoadSize = 60
            ),
            pagingSourceFactory = {
                LocationsPagingSource(locationApiService)
            }
        ).flow

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
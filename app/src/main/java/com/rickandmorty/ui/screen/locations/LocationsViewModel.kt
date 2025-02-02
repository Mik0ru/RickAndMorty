package com.rickandmorty.ui.screen.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rickandmorty.data.remote.repository.LocationRepository
import com.rickandmorty.data.remote.response.LocationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LocationsViewModel(
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _locationsFlow = MutableSharedFlow<PagingData<LocationResponse>>(1)
    val locationsFlow = _locationsFlow.asSharedFlow()

    private val _locationFlow = MutableStateFlow<LocationResponse?>(null)
    val locationFlow = _locationFlow.asStateFlow()

    var savedScrollPosition = 0
    var savedScrollOffset = 0

    init {
        fetchLocations()
    }

    private fun fetchLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            locationRepository.getAllLocations()
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _locationsFlow.emit(pagingData)
                }
        }
    }

    fun refreshLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            locationRepository.getAllLocations()
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _locationsFlow.emit(pagingData)
                }
        }
    }

    fun getLocationById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _locationFlow.emit(locationRepository.getLocationById(id))
        }
    }
}
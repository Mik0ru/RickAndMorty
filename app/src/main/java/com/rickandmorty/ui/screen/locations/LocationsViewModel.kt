package com.rickandmorty.ui.screen.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickandmorty.data.remote.repository.LocationRepository
import com.rickandmorty.data.remote.response.LocationResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationsViewModel (
    private val locationRepository: LocationRepository
) : ViewModel() {
    private val _locationsFlow = MutableStateFlow<List<LocationResponse>?>(emptyList())
    val locationsFlow = _locationsFlow.asStateFlow()

    private val _locationFlow = MutableStateFlow<LocationResponse?>(null)
    val locationFlow = _locationFlow.asStateFlow()

    fun getLocations(){
        viewModelScope.launch {
            _locationsFlow.value = locationRepository.getLocations()
        }
    }

    fun getLocationById(id: Int){
        viewModelScope.launch {
            _locationFlow.value = locationRepository.getLocationById(id)
        }
    }
}
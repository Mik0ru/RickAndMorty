package com.rickandmorty.ui.screen.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickandmorty.data.remote.repository.EpisodeRepository
import com.rickandmorty.data.remote.response.EpisodeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EpisodesViewModel(
    private val episodesRepository: EpisodeRepository
) : ViewModel() {

    private val _episodesFlow = MutableStateFlow<List<EpisodeResponse>?>(emptyList())
    val episodesFlow = _episodesFlow.asStateFlow()

    private val _episodeFlow = MutableStateFlow<EpisodeResponse?>(null)
    val episodeFlow = _episodeFlow.asStateFlow()

    fun getEpisodes() {
        viewModelScope.launch {
            _episodesFlow.value = episodesRepository.getEpisodes()
        }
    }

    fun getEpisodeById(id: Int) {
        viewModelScope.launch {
            _episodeFlow.value = episodesRepository.getEpisodeById(id)
        }
    }
}
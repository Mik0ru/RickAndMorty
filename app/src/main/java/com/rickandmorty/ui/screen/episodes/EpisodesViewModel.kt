package com.rickandmorty.ui.screen.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rickandmorty.data.remote.repository.EpisodeRepository
import com.rickandmorty.data.remote.response.EpisodeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EpisodesViewModel(
    private val episodeRepository: EpisodeRepository
) : ViewModel() {

    private val _episodesFlow = MutableSharedFlow<PagingData<EpisodeResponse>>(1)
    val episodesFlow = _episodesFlow.asSharedFlow()

    private val _episodeFlow = MutableStateFlow<EpisodeResponse?>(null)
    val episodeFlow = _episodeFlow.asStateFlow()

    var savedScrollPosition = 0
    var savedScrollOffset = 0

    init {
        fetchEpisodes()
    }

    private fun fetchEpisodes() {
        viewModelScope.launch(Dispatchers.IO) {
            episodeRepository.getAllEpisodes()
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _episodesFlow.emit(pagingData)
                }
        }
    }

    fun refreshEpisodes() {
        viewModelScope.launch(Dispatchers.IO) {
            episodeRepository.getAllEpisodes()
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _episodesFlow.emit(pagingData)
                }
        }
    }

    fun getEpisodeById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _episodeFlow.emit(episodeRepository.getEpisodeById(id))
        }
    }
}
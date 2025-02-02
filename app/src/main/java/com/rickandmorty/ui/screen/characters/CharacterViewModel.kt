package com.rickandmorty.ui.screen.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rickandmorty.data.remote.repository.CharacterRepository
import com.rickandmorty.data.remote.response.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _charactersFlow = MutableSharedFlow<PagingData<CharacterResponse>>( 1)
    val charactersFlow = _charactersFlow.asSharedFlow()

    private val _characterFlow = MutableStateFlow<CharacterResponse?>(null)
    val characterFlow = _characterFlow.asStateFlow()

    var savedScrollPosition = 0
    var savedScrollOffset = 0
    init {
        fetchCharacters()
    }
    private fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            characterRepository.getAllCharacters()
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _charactersFlow.emit(pagingData)
                }
        }
    }
    fun refreshCharacters(){
        viewModelScope.launch(Dispatchers.IO) {
            characterRepository.getAllCharacters()
                .cachedIn(viewModelScope)
                .collectLatest{ pagingData ->
                    _charactersFlow.emit(pagingData)
                }
        }
    }

    fun getCharacterById(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            _characterFlow.emit(characterRepository.getCharacterById(id))
        }
    }

}
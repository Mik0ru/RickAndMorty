package com.rickandmorty.ui.screen.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickandmorty.data.remote.repository.CharacterRepository
import com.rickandmorty.data.remote.response.CharacterResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private val _charactersFlow = MutableStateFlow<List<CharacterResponse>?>(emptyList())
    val charactersFlow = _charactersFlow.asStateFlow()

    private val _characterFlow = MutableStateFlow<CharacterResponse?>(null)
    val characterFlow = _characterFlow.asStateFlow()

    fun getCharacters(){
        viewModelScope.launch {
            _charactersFlow.value = characterRepository.getCharacters()
        }
    }

    fun getCharacterById(id: Int){
        viewModelScope.launch {
            _characterFlow.value = characterRepository.getCharacterById(id)
        }
    }

}
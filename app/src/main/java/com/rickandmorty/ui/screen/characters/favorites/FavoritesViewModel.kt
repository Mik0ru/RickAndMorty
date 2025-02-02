package com.rickandmorty.ui.screen.characters.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickandmorty.data.local.database.dao.FavoritesDao
import com.rickandmorty.data.local.models.CharacterEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val dao: FavoritesDao
) : ViewModel() {
    private var _favoritesFlow = MutableStateFlow<List<CharacterEntity>?>(emptyList())
    val favoritesFlow = _favoritesFlow.asStateFlow()

    fun insert(character: CharacterEntity) = viewModelScope.launch {
        dao.insert(character)
    }
    fun delete(id: Int) = viewModelScope.launch {
        dao.delete(id)
    }
    fun getFavorites() = viewModelScope.launch {
        _favoritesFlow.value = dao.getAll()
    }

    fun onFavoriteClick(character: CharacterEntity) {
        if (favoritesFlow.value?.any { it.characterId == character.characterId } == true) {
            delete(character.characterId).invokeOnCompletion {
                getFavorites()
            }
        } else {
            insert(character).invokeOnCompletion {
                getFavorites()
            }
        }
    }

}
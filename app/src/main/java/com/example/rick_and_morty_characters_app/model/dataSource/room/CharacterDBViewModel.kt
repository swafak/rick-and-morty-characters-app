package com.example.rick_and_morty_characters_app.model.dataSource.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CharacterDBViewModell(private val repository: CharacterDBRepository) : ViewModel() {

    fun insert(favoriteChar : CharacterEntity) {
        viewModelScope.launch {
            repository.insert(favoriteChar)
        }
    }
    fun isFavorite(id: Int) = liveData {
        emit(repository.isFavorite(id))
    }


    fun deleteFavoriteById(id: Int) {
        viewModelScope.launch {
            repository.deleteFavoriteById(id)
        }
    }
}
class CharacterViewModelFactory(private val repository: CharacterDBRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterDBViewModell(repository) as T
    }
}

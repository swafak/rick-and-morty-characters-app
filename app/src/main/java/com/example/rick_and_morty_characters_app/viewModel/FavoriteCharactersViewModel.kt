package com.example.rick_and_morty_characters_app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterDBRepository
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterEntity

class FavoriteCharactersViewModel(private val repository: CharacterDBRepository) : ViewModel() {
    val favoriteCharacter: LiveData<List<CharacterEntity>> = liveData {
        emitSource(repository.getFavoriteChar())
    }
}

class FavoriteCharacterViewModelFactory(private val repository: CharacterDBRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoriteCharactersViewModel(repository) as T
    }
}


sealed class FavoriteViewState {
    /**
     * Network state loading.
     */
    object Loading : FavoriteViewState()

    /**
     * Network state Success.
     */
    object Success : FavoriteViewState()

    /**
     * Error occurred state.
     */
    data class Error(
        val errorMessage: String?,
        val stringResourceId: Int?,
        val errorCode: Int?
    ) : FavoriteViewState()

}
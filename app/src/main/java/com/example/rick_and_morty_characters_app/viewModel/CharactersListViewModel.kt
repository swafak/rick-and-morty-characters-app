package com.example.rick_and_morty_characters_app.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_morty_characters_app.model.dataSource.network.CharacterResult
import com.example.rick_and_morty_characters_app.model.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : ViewModel() {

    private val _characters = MutableLiveData<List<CharacterResult>>()
    val characters: LiveData<List<CharacterResult>> = _characters

    private val _viewState = MutableLiveData<CharacterListViewState>()
    val viewState: LiveData<CharacterListViewState> = _viewState

    init {
        fetchCharacters()
    }


fun fetchCharacters() {
    viewModelScope.launch(Dispatchers.IO) {
        _viewState.postValue(CharacterListViewState.Loading)
        try {
            val characterList = repository.getCharacters()
            withContext(Dispatchers.Main) {
                // Handle null safely using 'let'
                characterList?.let {
                    _characters.postValue(it)
                    _viewState.postValue(CharacterListViewState.Success)
                } ?: run {
                    _viewState.postValue(CharacterListViewState.Error(NullPointerException("Character list is null")))
                }
            }
            } catch (e: Exception) {
                _viewState.postValue(CharacterListViewState.Error(e))
            }
        }

}

}

sealed class CharacterListViewState {
    object Loading : CharacterListViewState()
    object Success : CharacterListViewState()
    data class Error(val exception: Throwable) : CharacterListViewState() // Error expects an exception parameter
}

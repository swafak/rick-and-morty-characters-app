package com.example.rick_and_morty_characters_app.view.charactersList


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_morty_characters_app.model.dataSource.network.CharacterResult
import com.example.rick_and_morty_characters_app.model.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    private fun fetchCharacters() {
        viewModelScope.launch {
            _viewState.postValue(CharacterListViewState.Loading)
            try {
                val characterList = repository.getCharacters()
                _characters.postValue(characterList!!)
                _viewState.postValue(CharacterListViewState.Success)
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

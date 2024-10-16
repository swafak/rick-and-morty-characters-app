package com.example.rick_and_morty_characters_app.model.repository


import com.example.rick_and_morty_characters_app.model.dataSource.network.CharacterResult
import com.example.rick_and_morty_characters_app.model.dataSource.network.apiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RickAndMortyRepository @Inject constructor(private val api: apiService) {
        suspend fun getCharacters(): List<CharacterResult>? {
            val response = api.getCharacters()
            return if (response.isSuccessful) {
                response.body()?.results
            } else {
                null
            }
        }
    }

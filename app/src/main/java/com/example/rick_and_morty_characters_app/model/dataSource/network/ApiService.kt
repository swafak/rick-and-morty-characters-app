package com.example.rick_and_morty_characters_app.model.dataSource.network

import retrofit2.Response
import retrofit2.http.GET

interface apiService {

    @GET("character")
    suspend fun getCharacters(): Response<AllCharacterResponse>

}
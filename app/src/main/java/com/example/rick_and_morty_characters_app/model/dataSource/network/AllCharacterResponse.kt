package com.example.rick_and_morty_characters_app.model.dataSource.network

import java.io.Serializable

data class AllCharacterResponse(
    val info: Info,
    val results: List<CharacterResult>
): Serializable

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
): Serializable

data class Location(
    val name: String,
    val url: String
): Serializable

data class Origin(
    val name: String,
    val url: String
): Serializable


data class CharacterResult(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) : Serializable
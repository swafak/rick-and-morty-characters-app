package com.example.rick_and_morty_characters_app.model.dataSource.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "FavCharacters")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val created: String,
    val gender: String,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String

) : Serializable


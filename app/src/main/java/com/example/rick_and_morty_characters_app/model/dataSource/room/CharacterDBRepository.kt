package com.example.rick_and_morty_characters_app.model.dataSource.room

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CharacterDBRepository(private val CharacterDao: CharacterDao) {
    suspend fun insert(favoriteChar: CharacterEntity) {
        withContext(Dispatchers.IO) {
            CharacterDao.insert(favoriteChar)
        }
    }
    suspend fun deleteFavoriteById(id: Int) {
        CharacterDao.deleteFavoriteById(id)
    }
    suspend fun isFavorite(id: Int): Boolean {
        return CharacterDao.isFavorite(id) != null
    }

    fun getFavoriteChar(): LiveData<List<CharacterEntity>> {
        return CharacterDao.getAllFavorites()

    }
}

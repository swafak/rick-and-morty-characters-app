package com.example.rick_and_morty_characters_app.model.dataSource.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(FavoriteChar: CharacterEntity)

    @Query("SELECT * FROM FavCharacters")
    fun getAllFavorites(): LiveData<List<CharacterEntity>>

    @Query("DELETE FROM FavCharacters WHERE id = :id")
    suspend fun deleteFavoriteById(id: Int)

    @Query("SELECT * FROM FavCharacters WHERE id = :id LIMIT 1")
    suspend fun isFavorite(id: Int): CharacterEntity?
}
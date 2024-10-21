package com.example.rick_and_morty_characters_app

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterDB
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterDao
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var testDao: CharacterDao
    private lateinit var testDB: CharacterDB

    @Before
    fun setup(){

        val context = ApplicationProvider.getApplicationContext<Context>()
        testDB = Room.inMemoryDatabaseBuilder(
                context, CharacterDB::class.java
        ).allowMainThreadQueries().build()
        testDao = testDB.CharacterDao()

    }

    @After
    fun closeDb (){
        testDB.close()
    }

    @Test
    @Throws(Exception::class)
    fun Insert_favorite_character_to_the_databasw_check_size() = runTest{

        val newFavoriteCharacter = CharacterEntity(
            1,
            "created",
            "Male",
            "Morty.png",
            "morty",
            "Human",
            "Alive",
            "Human",
            "https://morty/",

       )
        testDao.insert(newFavoriteCharacter)
        val characters = testDao.getAllFavorites().getOrAwaitValue()
        assertThat(characters.size).isEqualTo(1)

    }
    @Test
    @Throws(Exception::class)
    fun delete_favorite_character_from_DB() = runTest {
        val newFavoriteCharacter = CharacterEntity(
            1,
            "created",
            "Male",
            "Rick.png",
            "Rick",
            "Human",
            "Alive",
            "Human",
            "https://Rick/",

            )

        testDao.insert(newFavoriteCharacter)
        testDao.deleteFavoriteById(newFavoriteCharacter.id)

        val characters = testDao.getAllFavorites().getOrAwaitValue()
        assertThat(characters.size).isEqualTo(0)

    }

    @Test
    @Throws(Exception::class)
    fun Insert_favorite_character_to_the_databasw_check_details()= runTest {
        val newFavoriteCharacter = CharacterEntity(
            1,
            "created",
            "Male",
            "Rick.png",
            "Rick",
            "Human",
            "Alive",
            "Human",
            "https://Rick/",

            )

        testDao.insert(newFavoriteCharacter)
        val characters = testDao.isFavorite(newFavoriteCharacter.id)
        assertThat(characters!!.id).isEqualTo(1)
        assertThat(characters.created).isEqualTo("created")
        assertThat(characters.gender).isEqualTo("Male")
        assertThat(characters.name).isEqualTo("Rick")
        assertThat(characters.type).isEqualTo("Human")
        assertThat(characters.image).isEqualTo("Rick.png")
        assertThat(characters.status).isEqualTo("Alive")
        assertThat(characters.species).isEqualTo("Human")
        assertThat(characters.url).isEqualTo("https://Rick/")
    }

}
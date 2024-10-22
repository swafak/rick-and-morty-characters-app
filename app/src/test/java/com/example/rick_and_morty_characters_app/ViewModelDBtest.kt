package com.example.rick_and_morty_characters_app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterDBRepository
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterDBViewModell
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterEntity
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class ViewModelDBtest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var testRepository: CharacterDBRepository
    private lateinit var characterDBViewModell: CharacterDBViewModell


    private val emptyLiveDataList = MutableLiveData<List<CharacterEntity>>()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup(){

        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)

        every {
            testRepository.getFavoriteChar()
        } returns emptyLiveDataList

        characterDBViewModell = CharacterDBViewModell(testRepository)
    }

    @After
    fun teardown(){
        Dispatchers.resetMain()
    }

    @Test
    fun Insert_in_Dao_once() = runTest {
        coEvery{
            testRepository.insert(any())
        } just Runs
//
//        launch {
//            characterDBViewModell.insert(any())
//        }
        advanceUntilIdle()
        coVerify(exactly = 1) { testRepository.insert(any()) }

    }



}
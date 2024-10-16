package com.example.rick_and_morty_characters_app.view.FavoriteCharacters

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rick_and_morty_characters_app.R

class FavoriteCharactersFragment : Fragment() {

    companion object {
        fun newInstance() = FavoriteCharactersFragment()
    }

    private val viewModel: FavoriteCharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_favorite_characters, container, false)
    }
}
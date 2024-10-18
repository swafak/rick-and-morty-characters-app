package com.example.rick_and_morty_characters_app.view.favoriteCharacters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rick_and_morty_characters_app.databinding.FragmentFavoriteCharactersBinding
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterDB
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterDBRepository
import com.example.rick_and_morty_characters_app.viewModel.FavoriteCharacterViewModelFactory
import com.example.rick_and_morty_characters_app.viewModel.FavoriteCharactersViewModel

class FavoriteCharactersFragment : Fragment() {



    private lateinit var viewModel: FavoriteCharactersViewModel
    private lateinit var adapter: FavoriteAdapter
    private lateinit var binding: FragmentFavoriteCharactersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initViews()

        val database = CharacterDB.getDatabase(requireContext())
        val repository = CharacterDBRepository(database.CharacterDao())

        val factory = FavoriteCharacterViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(FavoriteCharactersViewModel::class.java)

        adapter = FavoriteAdapter()
        binding.favRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.favRecyclerView.adapter = adapter

        viewModel.favoriteCharacter.observe(viewLifecycleOwner) { characters->
            adapter.submitList(characters)
        }
    }

//     override fun onDestroyView() {
//        super.onDestroyView()
//    }
}
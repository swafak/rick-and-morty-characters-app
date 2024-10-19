package com.example.rick_and_morty_characters_app.view.favoriteCharacters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rick_and_morty_characters_app.databinding.FragmentFavoriteCharactersBinding
import com.example.rick_and_morty_characters_app.model.dataSource.network.CharacterResult
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterDB
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterDBRepository
import com.example.rick_and_morty_characters_app.view.charactersDetails.CharacterAdapter
import com.example.rick_and_morty_characters_app.view.charactersDetails.CharactersDetailsFragment
import com.example.rick_and_morty_characters_app.viewModel.FavoriteCharacterViewModelFactory
import com.example.rick_and_morty_characters_app.viewModel.FavoriteCharactersViewModel

class FavoriteCharactersFragment : Fragment() {



    private lateinit var viewModel: FavoriteCharactersViewModel
    private lateinit var adapter: FavoriteAdapter
    private lateinit var binding: FragmentFavoriteCharactersBinding

    private lateinit var characterAdapter : CharacterAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteCharactersBinding.inflate(inflater, container, false)
        

        binding.detailFrame.visibility = View.GONE



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initViews()

        val database = CharacterDB.getDatabase(requireContext())
        val repository = CharacterDBRepository(database.CharacterDao())

        val factory = FavoriteCharacterViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(FavoriteCharactersViewModel::class.java)


//        characterAdapter = CharacterAdapter(
//            showDetails = { item ->
//                // Show details for the clicked character
//                showDetails(item)
//            }
//        )
        characterAdapter = CharacterAdapter { item->
            showDetails(item)
        }
        adapter = FavoriteAdapter()
        binding.favRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.favRecyclerView.adapter = adapter

        viewModel.favoriteCharacter.observe(viewLifecycleOwner) { characters->
            adapter.submitList(characters)
        }
    }
    private fun showDetails(item: CharacterResult) {
        Toast.makeText(context, "Show details", Toast.LENGTH_SHORT).show()
        val fragment = CharactersDetailsFragment.newInstance(item)
        binding.detailFrame.visibility = View.VISIBLE
        binding.favRecyclerView.visibility = View.GONE
        val tras = childFragmentManager.beginTransaction().replace(binding.detailFrame.id, fragment)
        tras.commit()
    }

//     override fun onDestroyView() {
//        super.onDestroyView()
//    }
}
package com.example.rick_and_morty_characters_app.view.charactersList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rick_and_morty_characters_app.databinding.FragmentCharactersListBinding
import com.example.rick_and_morty_characters_app.model.dataSource.network.CharacterResult
import com.example.rick_and_morty_characters_app.view.charactersDetails.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersListFragment : Fragment() {

    private lateinit var binding: FragmentCharactersListBinding
//    private lateinit var viewModel: CharactersListViewModel
private val viewModel: CharactersListViewModel by viewModels()
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersListBinding.inflate(inflater, container, false)
//        viewModel = ViewModelProvider(this)[CharactersListViewModel::class.java]
        // Set the ViewModel for data binding
        binding.viewModel =viewModel
            binding.lifecycleOwner = viewLifecycleOwner

        setupRecyclerView()

        return binding.root
    }
    private fun setupRecyclerView() {
        characterAdapter = CharacterAdapter { castcharacter ->
            // Handle click event here
            onCharacterClicked(castcharacter)
        }

        binding.characterRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterAdapter
            setHasFixedSize(true)
        }

        viewModel.characters.observe(viewLifecycleOwner, Observer { characters ->
            characterAdapter.submitList(characters)
        })
    }

    private fun onCharacterClicked(character: CharacterResult) {
        // Navigate to details or handle click event
    }

    private fun showShimmerEffect() {
        binding.shimmerFrameLayout.startShimmer()
        binding.shimmerFrameLayout.visibility = View.VISIBLE
        binding.characterRecyclerView.visibility = View.GONE
    }

    private fun hideShimmerEffect() {
        binding.shimmerFrameLayout.stopShimmer()
        binding.shimmerFrameLayout.visibility = View.GONE
        binding.characterRecyclerView.visibility = View.VISIBLE
    }
}


package com.example.rick_and_morty_characters_app.view.charactersList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rick_and_morty_characters_app.databinding.FragmentCharactersListBinding
import com.example.rick_and_morty_characters_app.model.dataSource.network.CharacterResult
import com.example.rick_and_morty_characters_app.view.charactersDetails.CharacterAdapter
import com.example.rick_and_morty_characters_app.view.charactersDetails.CharactersDetailsFragment
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
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.frameOne.visibility = View.GONE

        setupRecyclerView()


        return binding.root
    }
    private fun showDetails(item: CharacterResult) {
        Toast.makeText(context, "Show details", Toast.LENGTH_SHORT).show()
        val fragment = CharactersDetailsFragment.newInstance(item)
        binding.frameOne.visibility = View.VISIBLE
        binding.HomePage.visibility = View.GONE
        val tras = childFragmentManager.beginTransaction().replace(binding.frameOne.id, fragment)
        tras.commit()
    }

    private fun setupRecyclerView() {
        characterAdapter = CharacterAdapter(
            onItemClick = { castcharacter ->
                // Handle general item click event here
                onCharacterClicked(castcharacter)
            },
            showDetails = { castcharacter ->
                // Show details for the clicked character
                showDetails(castcharacter)
            }
        )
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


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
import com.example.rick_and_morty_characters_app.viewModel.CharacterListViewState
import com.example.rick_and_morty_characters_app.viewModel.CharactersListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CharactersListFragment : Fragment() {

    private lateinit var binding: FragmentCharactersListBinding

    private val viewModel: CharactersListViewModel by viewModels()
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersListBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.detailFrame.visibility = View.GONE

        setupRecyclerView()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        showShimmerEffect()
        viewModel.fetchCharacters()
        setUpObservers()
    }

    private fun setUpObservers() {
    viewModel.viewState.observe(viewLifecycleOwner, ::onViewStateChanged)
    }

    private fun showDetails(item: CharacterResult) {
        Toast.makeText(context, "Show details", Toast.LENGTH_SHORT).show()
        val fragment = CharactersDetailsFragment.newInstance(item)
        binding.detailFrame.visibility = View.VISIBLE
        binding.HomePage.visibility = View.GONE
        val tras = childFragmentManager.beginTransaction().replace(binding.detailFrame.id, fragment)
        tras.commit()
    }

    private fun setupRecyclerView(){
            characterAdapter = CharacterAdapter(

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

    private fun onViewStateChanged(state: CharacterListViewState) {
        hideShimmerEffect()
        when (state) {
            is CharacterListViewState.Loading -> {
                showShimmerEffect()
            }
            is CharacterListViewState.Success -> {
                hideShimmerEffect()
            }
            is CharacterListViewState.Error -> {
                hideShimmerEffect()
                // Handle error state
            }
        }
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



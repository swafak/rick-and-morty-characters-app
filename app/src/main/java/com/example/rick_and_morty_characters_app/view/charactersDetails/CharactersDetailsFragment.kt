package com.example.rick_and_morty_characters_app.view.charactersDetails

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.rick_and_morty_characters_app.MainActivity
import com.example.rick_and_morty_characters_app.R
import com.example.rick_and_morty_characters_app.databinding.FragmentCharactersDetailsBinding
import com.example.rick_and_morty_characters_app.model.dataSource.network.CharacterResult
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterDB
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterDBRepository
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterDBViewModell
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterEntity
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CharactersDetailsFragment : Fragment() {


    private val Details = "details"
    private var details: CharacterResult? = null
    private lateinit var binding: FragmentCharactersDetailsBinding

    private lateinit var favViewModel: CharacterDBViewModell


    private var selectedItem: CharacterResult? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            details = it.getSerializable(Details) as CharacterResult


        }
        //DB initialization
        val database = CharacterDB.getDatabase(requireContext())
        val repository = CharacterDBRepository(database.CharacterDao())
        val factory = CharacterViewModelFactory(repository)

        favViewModel = ViewModelProvider(this, factory).get(CharacterDBViewModell::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersDetailsBinding.inflate(inflater, container, false)
        binding.backPress.setOnClickListener{
            val intent = Intent(requireContext(), MainActivity::class.java)
            requireContext().startActivity(intent)
        }
        binding.apply {

            Name.text = details!!.name
            Status2.text = details!!.status
            Species2.text = details!!.species
            Gender2.text = details!!.gender
            Location2.text = details!!.location.name
            Glide.with(Image.context)
                .load(details!!.image)
                .into(Image)

        }


        binding.FavButton.setOnClickListener{

            selectedItem?.let {
                selectedItem?.let { item ->
                    toggleFavorite(item)
                }
            }

        }
        return binding.root
    }

    private fun addToFavorite(item: CharacterResult){
        val CharacterEntity = CharacterEntity(
            id = item.id,
            name = item.name,
            gender = item.gender,
            status = item.status,
            image = item.image,
            species = item.species,
            created = item.created,
                      url = item.url,
            type = item.type

        )
        favViewModel.insert(CharacterEntity)

    }
    private fun removeFromFavorite(item: CharacterResult){
        favViewModel.deleteFavoriteById(item.id!!)
    }

    private fun toggleFavorite(item: CharacterResult) {
        item.id?.let {
            favViewModel.isFavorite(it).observe(viewLifecycleOwner, Observer { isFavorite ->
                if (isFavorite) {
                    Toast.makeText(context, "Removed from favorite", Toast.LENGTH_SHORT).show()
                    removeFromFavorite(item)
                    updateFavoriteIcon(false)
                } else {
                    addToFavorite(item)
                    Toast.makeText(context, "Added to favorite", Toast.LENGTH_SHORT).show()
                    updateFavoriteIcon(true)
                }
            })
        }
    }
    private fun updateFavoriteIcon(isFavorite: Boolean) {
        val color = if (isFavorite) R.color.primary_green else R.color.white
        binding.FavButton.setColorFilter(ContextCompat.getColor(requireContext(), color))
    }
    companion object{
        fun newInstance(details: CharacterResult) = CharactersDetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(Details,details)
            }
        }
    }
}


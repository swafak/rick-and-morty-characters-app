package com.example.rick_and_morty_characters_app.view.charactersDetails

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.rick_and_morty_characters_app.MainActivity
import com.example.rick_and_morty_characters_app.databinding.FragmentCharactersDetailsBinding
import com.example.rick_and_morty_characters_app.model.dataSource.network.CharacterResult

class CharactersDetailsFragment : Fragment() {


    private val Details = "details"
    private var details: CharacterResult? = null
    private lateinit var binding: FragmentCharactersDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            details = it.getSerializable(Details) as CharacterResult
        }
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
        return binding.root
    }
    companion object{
        fun newInstance(details: CharacterResult) = CharactersDetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(Details,details)
            }
        }
    }
}


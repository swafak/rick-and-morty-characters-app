package com.example.rick_and_morty_characters_app.view.favoriteCharacters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rick_and_morty_characters_app.databinding.FavCharacterRecViewBinding
import com.example.rick_and_morty_characters_app.model.dataSource.room.CharacterEntity

class FavoriteAdapter : ListAdapter<CharacterEntity, FavoriteAdapter.CharacterViewHolder>(
    DiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):CharacterViewHolder {
        val binding = FavCharacterRecViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
//        holder.binding.posterImageView.setOnClickListener {
//            showDetails(item)
//        }
    }

    class CharacterViewHolder(private val binding: FavCharacterRecViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CharacterEntity) {

            binding.Name.text = item.name
            binding.CharStatus.text = item.status

            Glide.with(binding.posterImageView.context)
                .load(item.image)
                .into(binding.posterImageView)

                     }

//            binding.executePendingBindings()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CharacterEntity>() {
        override fun areItemsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
            return oldItem == newItem
        }
    }

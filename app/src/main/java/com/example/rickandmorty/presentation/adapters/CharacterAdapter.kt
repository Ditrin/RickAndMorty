package com.example.rickandmorty.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.rickandmorty.data.DTOmodels.Result
import com.example.rickandmorty.databinding.ItemCharacterBinding

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    private var characters = mutableListOf<Result>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(characters[position]) }
        }
    }

    override fun getItemCount(): Int = characters.size

    inner class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Result) {
            with(binding) {
                statusCharacter.text = character.status
                genderCharacter.text = character.gender
                speciesCharacter.text = character.species
                nameCharacter.text = character.name

                Glide.with(itemView)
                    .load(character.image)
                    .transform(CircleCrop())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageCharacter)
            }
        }
    }

    fun setCharacterList(character: List<Result>) {
        characters = character.toMutableList()
        notifyDataSetChanged()
    }

    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setOnClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }
}
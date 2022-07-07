package com.example.rickandmorty.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemSingleBinding

class SingleAdapter : RecyclerView.Adapter<SingleAdapter.ViewHolder>() {
    private var singleCharacter: List<String> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSingleBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SingleAdapter.ViewHolder, position: Int) {
        holder.bind(singleCharacter[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(singleCharacter[position]) }
        }
    }

    override fun getItemCount(): Int = singleCharacter.size

    inner class ViewHolder(private val binding: ItemSingleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(singleCharacter: String) {
            with(binding) {
                single.text = singleCharacter
            }
        }
    }

    fun setSingleCharacter(characterSingle: List<String>) {
        singleCharacter = characterSingle
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}
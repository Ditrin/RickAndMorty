package com.example.rickandmorty.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.DTOmodels.ResultXX
import com.example.rickandmorty.databinding.ItemEpisodeBinding

class EpisodesAdapter : RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {
    private var episodes: List<ResultXX> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemEpisodeBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodesAdapter.ViewHolder, position: Int) {
        holder.bind(episodes[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(episodes[position]) }
        }
    }

    override fun getItemCount(): Int = episodes.size

    inner class ViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(episode: ResultXX) {
            with(binding) {
                nameEpisodes.text = episode.name
                airDateEpisodes.text = episode.air_date
                episodeEpisodes.text = episode.episode
            }
        }
    }

    fun setEpisodesList(episode: List<ResultXX>) {
        episodes = episode
    }

    private var onItemClickListener: ((ResultXX) -> Unit)? = null

    fun setOnClickListener(listener: (ResultXX) -> Unit) {
        onItemClickListener = listener
    }
}
package com.example.rickandmorty.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.DTOmodels.Locations
import com.example.rickandmorty.data.DTOmodels.Result
import com.example.rickandmorty.data.DTOmodels.ResultX
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.databinding.ItemLocationsBinding

class LocationsAdaptor: RecyclerView.Adapter<LocationsAdaptor.ViewHolder>() {
        private var locations: List<ResultX> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsAdaptor.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLocationsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationsAdaptor.ViewHolder, position: Int) {
        holder.bind(locations[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(locations[position]) }
        }
    }

    override fun getItemCount(): Int = locations.size

    inner class ViewHolder(private val binding: ItemLocationsBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(loc: ResultX){
                    with(binding){
                        nameLocations.text = loc.name
                        typeLocations.text = loc.type
                        dimensionLocations.text = loc.dimension
                    }
                }
            }
    fun setLocationsList(loc: List<ResultX>)
    {
        locations = loc
    }
    private var onItemClickListener: ((ResultX) -> Unit)? = null

    fun setOnClickListener(listener: (ResultX) -> Unit) {
        onItemClickListener = listener
    }
}
package com.example.rickandmorty.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentLocationsBinding
import com.example.rickandmorty.presentation.adapters.LocationsAdaptor
import com.example.rickandmorty.presentation.fragments.CharactersFragment.idId.idLocation
import com.example.rickandmorty.presentation.viewmodels.LocationsViewModel

class LocationsFragment : Fragment(R.layout.fragment_locations) {
    private lateinit var binding: FragmentLocationsBinding
    private val viewModel: LocationsViewModel by viewModels()
    private lateinit var listAdapter: LocationsAdaptor
    private lateinit var swipe: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationsBinding.inflate(inflater, container, false)
        swipe = binding.swipeLocation
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = LocationsAdaptor()
        viewModel.getLocationsList()
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.pbLocation.visibility = View.VISIBLE
            } else
                binding.pbLocation.visibility = View.GONE
        }

        viewModel.locationsList.observe(viewLifecycleOwner) {
            binding.recyclerViewLocations.apply {
                adapter = listAdapter
                layoutManager = GridLayoutManager(activity, 2)
                val dividerItemDecoration = DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
                addItemDecoration(dividerItemDecoration)
            }
            listAdapter.setLocationsList(it)
            listAdapter.setOnClickListener {
                parentFragmentManager.beginTransaction().apply {
                    idLocation = it.id
                    replace(R.id.fragmentContainer, DetailLocFragment())
                    addToBackStack(LocationsFragment().tag)
                    commit()
                }
            }
        }
        swipe.setOnRefreshListener {
            viewModel.refreshList()
            swipe.isRefreshing = false
        }

        binding.buttonSearchLocation.setOnClickListener {
            viewModel.saveText(binding.searchLocation.text.toString())
            viewModel.searchText.observe(viewLifecycleOwner) { text ->
                viewModel.getSearchList(text)
                listAdapter.update()
            }
        }
    }
}
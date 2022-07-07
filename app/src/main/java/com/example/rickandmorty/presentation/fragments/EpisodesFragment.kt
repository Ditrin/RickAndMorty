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
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentEpisodesBinding
import com.example.rickandmorty.presentation.adapters.EpisodesAdapter
import com.example.rickandmorty.presentation.viewmodels.EpisodesViewModel

class EpisodesFragment : Fragment(R.layout.fragment_episodes) {
    private lateinit var binding: FragmentEpisodesBinding
    private val viewModel: EpisodesViewModel by viewModels()
    private lateinit var listAdapter: EpisodesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = EpisodesAdapter()
        viewModel.getEpisodesList()
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.pbEpisodes.visibility = View.VISIBLE
            } else
                binding.pbEpisodes.visibility = View.GONE
        }

        viewModel.episodesList.observe(viewLifecycleOwner) {
            binding.recyclerViewEpisodes.apply {
                adapter = listAdapter
                layoutManager = GridLayoutManager(activity, 2)
                val dividerItemDecoration = DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
                addItemDecoration(dividerItemDecoration)
            }
            listAdapter.setEpisodesList(it)
            listAdapter.setOnClickListener {
                parentFragmentManager.beginTransaction().apply {
                    CharactersFragment.idId.idEpisode = it.id
                    replace(R.id.fragmentContainer, DetailEpisodeFragment())
                    addToBackStack(EpisodesFragment().tag)
                    commit()
                }
            }
        }
        binding.swipeEpisodes.setOnRefreshListener {
            viewModel.getEpisodesList()
            binding.swipeEpisodes.isRefreshing = false
        }

        binding.buttonSearchEpisodes.setOnClickListener {
            viewModel.saveText(binding.searchEpisodes.text.toString())
            viewModel.searchText.observe(viewLifecycleOwner) { text ->
                viewModel.getSearchList(text)
            }
        }
    }
}
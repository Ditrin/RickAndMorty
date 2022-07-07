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
import com.example.rickandmorty.databinding.FragmentDetailEpisodesBinding
import com.example.rickandmorty.databinding.FragmentDetailLocBinding
import com.example.rickandmorty.presentation.adapters.SingleAdapter
import com.example.rickandmorty.presentation.fragments.CharactersFragment.idId.idCharacter
import com.example.rickandmorty.presentation.viewmodels.DetailEpisodesViewModel
import com.example.rickandmorty.presentation.viewmodels.DetailLocViewModel

class DetailEpisodeFragment: Fragment() {
    private lateinit var binding: FragmentDetailEpisodesBinding
    private val viewModel: DetailEpisodesViewModel by viewModels()
    private lateinit var listAdapter: SingleAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailEpisodesBinding.inflate(inflater, container, false)
        binding.appBarEpisode.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = SingleAdapter()
        viewModel.getSingleEpisode(CharactersFragment.idId.idEpisode)
        viewModel.isLoading.observe(viewLifecycleOwner){
            if(it){
                binding.pbDetailEpisodes.visibility = View.VISIBLE

            }
            else
                binding.pbDetailEpisodes.visibility = View.GONE

        }

        viewModel.singleEpisode.observe(viewLifecycleOwner){
            binding.detailEpisodesRecyclerView.apply {
                adapter = listAdapter
                layoutManager = GridLayoutManager(activity, 2)
                val dividerItemDecoration = DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
                addItemDecoration(dividerItemDecoration)
            }
            listAdapter.setSingleCharacter(it.characters)
            listAdapter.setOnClickListener {
                parentFragmentManager.beginTransaction().apply {
                    idCharacter = it.replace("https://rickandmortyapi.com/api/character/", "").toInt()
                    replace(R.id.fragmentContainer, DetailInfoFragment())
                    commit()
                }
            }
            with(binding){
                nameDetailEpisodes.text = it.name
                airDateDetailEpisodes.text = it.air_date
                episodeDetailEpisodes.text = it.episode

            }}
    }
}
package com.example.rickandmorty.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentDetailInfoBinding
import com.example.rickandmorty.presentation.adapters.SingleAdapter
import com.example.rickandmorty.presentation.fragments.CharactersFragment.idId.idCharacter
import com.example.rickandmorty.presentation.fragments.CharactersFragment.idId.idEpisode
import com.example.rickandmorty.presentation.viewmodels.DetailInfoViewModel

class DetailInfoFragment : Fragment(R.layout.fragment_detail_info) {
    private lateinit var binding: FragmentDetailInfoBinding
    private val viewModel: DetailInfoViewModel by viewModels()
    private lateinit var listAdapter: SingleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailInfoBinding.inflate(inflater, container, false)
        binding.appBarInfo.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = SingleAdapter()
        viewModel.getSingleCharacter(idCharacter)
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.pbDetailInfo.visibility = View.VISIBLE

            } else
                binding.pbDetailInfo.visibility = View.GONE

        }


        viewModel.singleCharacter.observe(viewLifecycleOwner) {
            binding.episodeSingle.apply {
                adapter = listAdapter
                layoutManager = LinearLayoutManager(requireContext())
                val dividerItemDecoration = DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
                addItemDecoration(dividerItemDecoration)
            }
            listAdapter.setSingleCharacter(it.episode)
            listAdapter.setOnClickListener {
                parentFragmentManager.beginTransaction().apply {
                    idEpisode = it.replace("https://rickandmortyapi.com/api/episode/", "").toInt()
                    replace(R.id.fragmentContainer, DetailEpisodeFragment())
                    commit()
                }
            }
            with(binding) {
                speciesSingle.text = it.species
                nameCharacterSingle.text = it.name
                statusSingle.text = it.status
                genderSingle.text = it.gender
                locationSingle.text = it.location.name

                Glide.with(this@DetailInfoFragment)
                    .load(it.image)
                    .transform(CircleCrop())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageCharacter)

            }

        }
    }
}
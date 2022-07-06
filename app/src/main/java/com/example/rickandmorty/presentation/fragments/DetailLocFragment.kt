package com.example.rickandmorty.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentDetailLocBinding
import com.example.rickandmorty.presentation.adapters.SingleAdapter
import com.example.rickandmorty.presentation.fragments.CharactersFragment.idId.idLocation
import com.example.rickandmorty.presentation.viewmodels.DetailLocViewModel

class DetailLocFragment : Fragment(R.layout.fragment_detail_loc) {
    private lateinit var binding: FragmentDetailLocBinding
    private val viewModel: DetailLocViewModel by viewModels()
    private lateinit var listAdapter: SingleAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailLocBinding.inflate(inflater, container, false)
        binding.appBarLocation.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = SingleAdapter()
        viewModel.getSingleLocCharacter(idLocation)
        viewModel.isLoading.observe(viewLifecycleOwner){
            if(it){
                binding.pbDetailLocation.visibility = View.VISIBLE

            }
            else
                binding.pbDetailLocation.visibility = View.GONE

        }


        viewModel.singleLocation.observe(viewLifecycleOwner){
            binding.detailLocationRecyclerView.apply {
                adapter = listAdapter
                layoutManager = LinearLayoutManager(requireContext())
                val dividerItemDecoration = DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
                addItemDecoration(dividerItemDecoration)
            }
            listAdapter.setSingleCharacter(it.residents)
            listAdapter.setOnClickListener {
                parentFragmentManager.beginTransaction().apply {
                    CharactersFragment.idId.idCharacter = it.replace("https://rickandmortyapi.com/api/character/", "").toInt()
                    replace(R.id.fragmentContainer, DetailInfoFragment())
                    commit()
                }
        }
            with(binding){
                nameDetailLoc.text = it.name
                typeDetailLoc.text = it.type
                dimensionDetailLoc.text = it.dimension

            }}
    }

}
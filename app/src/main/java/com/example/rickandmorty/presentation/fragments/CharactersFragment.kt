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
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.presentation.adapters.CharacterAdapter
import com.example.rickandmorty.presentation.fragments.CharactersFragment.idId.idCharacter
import com.example.rickandmorty.presentation.viewmodels.CharacterViewModel

class CharactersFragment: Fragment(R.layout.fragment_characters) {
    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharacterViewModel by viewModels()
    private lateinit var listAdapter: CharacterAdapter
    private lateinit var swipe: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        swipe = binding.swipeCharacters
        return binding.root
    }

    object idId{
        var idCharacter:Int = 0
        var idLocation: Int = 0
        var idEpisode: Int = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = CharacterAdapter()
        viewModel.getCharacterList()
        viewModel.isLoading.observe(viewLifecycleOwner){
            if(it){
                binding.pbCharacter.visibility = View.VISIBLE
            }
            else
                binding.pbCharacter.visibility = View.GONE
        }

        viewModel.listCharacter.observe(viewLifecycleOwner){
            binding.recyclerView.apply {
                adapter = listAdapter
                layoutManager = GridLayoutManager(activity, 2)
                val dividerItemDecoration = DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
                addItemDecoration(dividerItemDecoration)
            }
            listAdapter.setCharacterList(it)

            listAdapter.setOnClickListener {result->
                parentFragmentManager.beginTransaction().apply  {
                    idCharacter = result.id
                    replace(R.id.fragmentContainer, DetailInfoFragment())
                    addToBackStack(CharactersFragment().tag)
                    commit()
                }
            }
        }

        swipe.setOnRefreshListener {
            viewModel.refreshList()
            swipe.isRefreshing = false
        }

        binding.buttonSearchCharacter.setOnClickListener{
            viewModel.saveText(binding.searchCharacter.text.toString())
            viewModel.searchText.observe(viewLifecycleOwner){text->
                viewModel.getSearchList(text)
            }
        }
    }
}
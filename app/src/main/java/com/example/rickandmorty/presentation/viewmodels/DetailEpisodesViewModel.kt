package com.example.rickandmorty.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.DTOmodels.SingleEpisode
import com.example.rickandmorty.data.repository.RickAndMortyRepository
import com.example.rickandmorty.presentation.fragments.CharactersFragment
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailEpisodesViewModel: ViewModel() {
    private val singleEpisodeLiveData = MutableLiveData<SingleEpisode>()
    val singleEpisode: LiveData<SingleEpisode> = singleEpisodeLiveData
    private var job: Job? = null
    private val repository = RickAndMortyRepository()
    private val isLoadingLiveData = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = isLoadingLiveData


    fun getSingleEpisode(id: Int){
        job?.cancel()
        job = viewModelScope.launch {
            kotlin.runCatching {
                repository.getSingleEpisodes(id)
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                singleEpisodeLiveData.postValue(it)
            }.onFailure {
                isLoadingLiveData.postValue(false)
                val tmp = it
                val id = CharactersFragment.idId.idEpisode
                print(tmp)
            }
        }
    }

}
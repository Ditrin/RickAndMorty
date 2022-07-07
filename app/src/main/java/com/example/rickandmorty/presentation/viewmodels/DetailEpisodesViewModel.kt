package com.example.rickandmorty.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.DTOmodels.SingleEpisode
import com.example.rickandmorty.data.repository.RickAndMortyRepository
import com.example.rickandmorty.domain.CharacterMapper
import com.example.rickandmorty.domain.EpisodeMapper
import com.example.rickandmorty.presentation.fragments.CharactersFragment
import com.example.rickandmorty.presentation.fragments.CharactersFragment.idId.idEpisode
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailEpisodesViewModel : ViewModel() {
    private val singleEpisodeLiveData = MutableLiveData<SingleEpisode>()
    val singleEpisode: LiveData<SingleEpisode> = singleEpisodeLiveData
    private var job: Job? = null
    private val repository = RickAndMortyRepository()
    private val isLoadingLiveData = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = isLoadingLiveData


    fun getSingleEpisode(id: Int) {
        job?.cancel()
        job = viewModelScope.launch {
            kotlin.runCatching {
                repository.getSingleEpisodes(id)
            }.onSuccess {
                repository.insertEpisode(EpisodeMapper().map(it))
                isLoadingLiveData.postValue(false)
                singleEpisodeLiveData.postValue(it)
            }.onFailure {
                try {
                    val temp = repository.getEpisodeById(idEpisode)
                    singleEpisodeLiveData.value = temp.toSingleEpisode()
                    isLoadingLiveData.postValue(false)
                    val tmp = it
                    val id = CharactersFragment.idId.idEpisode
                    print(tmp)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
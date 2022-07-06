package com.example.rickandmorty.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.DTOmodels.ResultXX
import com.example.rickandmorty.data.repository.RickAndMortyRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EpisodesViewModel: ViewModel() {
    private val episodesLiveData = MutableLiveData<List<ResultXX>>()
    val episodesList: LiveData<List<ResultXX>> = episodesLiveData
    private var job: Job? = null
    private val repository = RickAndMortyRepository()
    private val isLoadingLiveData = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = isLoadingLiveData
    private val searchTextLiveData = MutableLiveData<String>()
    val searchText: LiveData<String> = searchTextLiveData

    fun saveText(text: String){
        searchTextLiveData.postValue("")
        searchTextLiveData.postValue(text)
    }

    fun getEpisodesList() {
        job?.cancel()
        job = viewModelScope.launch {
            kotlin.runCatching {
                repository.getAllEpisodes()
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                episodesLiveData.postValue(it)
            }.onFailure {
                isLoadingLiveData.postValue(false)
                val tmp = it
                print(tmp)
            }
        }
    }

    fun getSearchList(name: String) {
        job?.cancel()
        job = GlobalScope.launch {//не работает с viewModelScope, зато работает с Global, магия)))
            kotlin.runCatching {
                repository.getSearchEpisodes(name)
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                episodesLiveData.postValue(it)
            }.onFailure {
                isLoadingLiveData.postValue(false)
                val tmp = it
            }
        }
    }
}
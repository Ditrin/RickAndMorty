package com.example.rickandmorty.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.DTOmodels.ResultX
import com.example.rickandmorty.data.repository.RickAndMortyRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LocationsViewModel : ViewModel() {
    private val locationsLiveData = MutableLiveData<List<ResultX>>()
    val locationsList: LiveData<List<ResultX>> = locationsLiveData
    private var job: Job? = null
    private val repository = RickAndMortyRepository()
    private val isLoadingLiveData = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = isLoadingLiveData
    private val searchTextLiveData = MutableLiveData<String>()
    val searchText: LiveData<String> = searchTextLiveData

    fun saveText(text: String) {
        searchTextLiveData.postValue("")
        searchTextLiveData.postValue(text)
    }

    fun refreshList() {
        locationsLiveData.postValue(emptyList())
        job?.cancel()
        job = GlobalScope.launch {
            kotlin.runCatching {
                repository.getAllLocation()
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                locationsLiveData.postValue(it)
            }.onFailure {
                isLoadingLiveData.postValue(false)
                val tmp = it
                print(tmp)
            }
        }
    }

    fun getLocationsList() {
        job?.cancel()
        job = GlobalScope.launch {
            kotlin.runCatching {
                repository.getAllLocation()
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                locationsLiveData.postValue(it)
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
                repository.getSearchLocation(name)
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                locationsLiveData.postValue(it)
            }.onFailure {
                isLoadingLiveData.postValue(false)
                val tmp = it
            }
        }
    }
}
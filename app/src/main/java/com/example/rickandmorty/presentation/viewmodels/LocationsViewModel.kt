package com.example.rickandmorty.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.DTOmodels.ResultX
import com.example.rickandmorty.data.repository.RickAndMortyRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LocationsViewModel: ViewModel() {
    private val locationsLiveData = MutableLiveData<List<ResultX>>()
    val locationsList: LiveData<List<ResultX>> = locationsLiveData
    private var job: Job? = null
    private val repository = RickAndMortyRepository()
    private val isLoadingLiveData = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = isLoadingLiveData

    fun getLocationsList(){
        job?.cancel()
        job = viewModelScope.launch {
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
}
package com.example.rickandmorty.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.DTOmodels.SingleLocation
import com.example.rickandmorty.data.repository.RickAndMortyRepository
import com.example.rickandmorty.domain.CharacterMapper
import com.example.rickandmorty.domain.LocationMapper
import com.example.rickandmorty.presentation.fragments.CharactersFragment
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailLocViewModel : ViewModel() {
    private val singleLocationsLiveData = MutableLiveData<SingleLocation>()
    val singleLocation: LiveData<SingleLocation> = singleLocationsLiveData
    private var job: Job? = null
    private val repository = RickAndMortyRepository()
    private val isLoadingLiveData = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = isLoadingLiveData

    fun getSingleLocCharacter(id: Int){
        job?.cancel()
        job = viewModelScope.launch {
            kotlin.runCatching {
                repository.getSingleLocation(id)
            }.onSuccess {
                repository.insertLocation(LocationMapper().map(it))
                isLoadingLiveData.postValue(false)
                singleLocationsLiveData.postValue(it)
            }.onFailure {
                val temp = repository.getLocationById(CharactersFragment.idId.idLocation)
                singleLocationsLiveData.value =  temp.toSingleLocation()
                isLoadingLiveData.postValue(false)
                val tmp = it
                val id = CharactersFragment.idId.idLocation
                print(tmp)
            }
        }
    }

}
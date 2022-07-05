package com.example.rickandmorty.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.DTOmodels.Result
import com.example.rickandmorty.data.repository.RickAndMortyRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CharacterViewModel: ViewModel() {
    private val listCharacterLiveData = MutableLiveData<List<Result>>()
    val listCharacter:LiveData<List<Result>> = listCharacterLiveData
    private var job: Job? = null
    private val repository = RickAndMortyRepository()
    private val isLoadingLiveData = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = isLoadingLiveData

    fun getCharacterList(){
        job?.cancel()
        job = viewModelScope.launch {
            kotlin.runCatching {
                repository.getListCharacter()
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                listCharacterLiveData.postValue(it)
            }.onFailure {
                isLoadingLiveData.postValue(false)
                val tmp = it
            }
        }
    }
}
package com.example.rickandmorty.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.DTOmodels.SingleCharacter
import com.example.rickandmorty.data.repository.RickAndMortyRepository
import com.example.rickandmorty.presentation.fragments.CharactersFragment.idId.idCharacter
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailInfoViewModel: ViewModel() {
    private val singleCharacterLiveData = MutableLiveData<SingleCharacter>()
    val singleCharacter: LiveData<SingleCharacter> = singleCharacterLiveData
    private var job: Job? = null
    private val repository = RickAndMortyRepository()
    private val isLoadingLiveData = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = isLoadingLiveData

    fun getSingleCharacter(id: Int){
        job?.cancel()
        job = viewModelScope.launch {
            kotlin.runCatching {
                repository.getSingleCharacter(id)
            }.onSuccess {
                isLoadingLiveData.postValue(false)
                singleCharacterLiveData.postValue(it)
            }.onFailure {
                isLoadingLiveData.postValue(false)
                val tmp = it
                val id = idCharacter
                print(tmp)
            }
        }
    }
}
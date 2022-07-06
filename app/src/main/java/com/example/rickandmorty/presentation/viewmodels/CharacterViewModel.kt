package com.example.rickandmorty.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.DTOmodels.Result
import com.example.rickandmorty.data.repository.RickAndMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class CharacterViewModel: ViewModel() {
    private val listCharacterLiveData = MutableLiveData<List<Result>>()
    val listCharacter:LiveData<List<Result>> = listCharacterLiveData
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



    fun refreshList(){
        listCharacterLiveData.postValue(emptyList())
        job?.cancel()
        job = GlobalScope.launch {
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

    fun getSearchList(name: String){
        job?.cancel()
        job = GlobalScope.launch {//не работает с viewModelScope, зато работает с Global, магия)))
            kotlin.runCatching {
                repository.getSearchCharacter(name)
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
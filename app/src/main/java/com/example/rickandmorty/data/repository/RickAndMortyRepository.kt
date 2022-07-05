package com.example.rickandmorty.data.repository

import android.accounts.NetworkErrorException
import com.example.rickandmorty.data.DTOmodels.*
import com.example.rickandmorty.data.remote.Networking
import com.example.rickandmorty.data.remote.RickAndMortyAPI
import com.example.rickandmorty.presentation.fragments.CharactersFragment

class RickAndMortyRepository() {
    suspend fun getListCharacter(): List<Result>{
        return Networking.rickAndMortyAPI.getAllCharacters().results
    }

    suspend fun getSingleCharacter(id: Int): SingleCharacter{
        return Networking.rickAndMortyAPI.getSingleCharacter(id)
    }

    suspend fun getAllLocation(): List<ResultX>{
        return Networking.rickAndMortyAPI.getAllLocation().results
    }

    suspend fun getSingleLocation(id: Int): SingleLocation{
        return Networking.rickAndMortyAPI.getSingleLocation(id)
    }

    suspend fun getAllEpisodes():List<ResultXX>{
        return  Networking.rickAndMortyAPI.getAllEpisodes().results
    }

    suspend fun getSingleEpisodes(id: Int): SingleEpisode{
        return  Networking.rickAndMortyAPI.getSingleEpisode(id)
    }
}
package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.DTOmodels.*
import com.example.rickandmorty.data.database.DataBase
import com.example.rickandmorty.data.remote.Networking
import com.example.rickandmorty.domain.models.SingleCharacterEntity
import com.example.rickandmorty.domain.models.SingleEpisodeEntity
import com.example.rickandmorty.domain.models.SingleLocationEntity

class RickAndMortyRepository() {
    private val characterDao = DataBase.db.CharacterDao()
    private val locationDao = DataBase.db.LocationDao()
    private val episodeDao = DataBase.db.EpisodesDao()

    suspend fun insertCharacter(result: SingleCharacterEntity){
        characterDao.insertCharacter(result)
    }

    suspend fun getCharacterById(id: Int): SingleCharacterEntity{
        return characterDao.getCharactersById(id)
    }

    suspend fun insertLocation(result: SingleLocationEntity){
        locationDao.insertLocation(result)
    }

    suspend fun getLocationById(id: Int): SingleLocationEntity{
        return locationDao.getLocationById(id)
    }

    suspend fun insertEpisode(result: SingleEpisodeEntity){
        episodeDao.insertEpisode(result)
    }

    suspend fun getEpisodeById(id: Int): SingleEpisodeEntity{
        return episodeDao.getEpisodeById(id)
    }

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

    suspend fun getSearchCharacter(name: String): List<Result>{
        return  Networking.rickAndMortyAPI.getSearchCharacter(name).results
    }

    suspend fun getSearchLocation(name: String): List<ResultX>{
        return  Networking.rickAndMortyAPI.getSearchLocation(name).results
    }

    suspend fun getSearchEpisodes(name: String): List<ResultXX>{
        return  Networking.rickAndMortyAPI.getSearchEpisodes(name).results
    }
}
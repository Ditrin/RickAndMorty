package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.DTOmodels.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyAPI {
    @GET("character")
    suspend fun getAllCharacters(): Characters

    @GET("location")
    suspend fun getAllLocation() : Locations

    @GET("location/{id}")
    suspend fun getSingleLocation(
        @Path("id") id: Int
    ): SingleLocation

    @GET("episode")
    suspend fun getAllEpisodes(): Episodes

    @GET("character/{id}")
    suspend fun getSingleCharacter(
        @Path("id") id: Int
    ): SingleCharacter

    @GET("episode/{id}")
    suspend fun getSingleEpisode(
        @Path("id") id: Int
    ): SingleEpisode

}
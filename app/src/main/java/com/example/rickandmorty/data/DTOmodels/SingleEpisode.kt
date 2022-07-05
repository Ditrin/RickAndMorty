package com.example.rickandmorty.data.DTOmodels

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SingleEpisode(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)
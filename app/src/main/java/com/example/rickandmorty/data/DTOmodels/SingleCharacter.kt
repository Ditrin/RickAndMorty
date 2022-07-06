package com.example.rickandmorty.data.DTOmodels

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class SingleCharacter(
    val id: Int,
    val created: String,
    val episode: List<String>,
    val gender: String,
    val image: String,
    val location: LocationX,
    val name: String,
    val origin: OriginX?,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
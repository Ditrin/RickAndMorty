package com.example.rickandmorty.data.DTOmodels

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Locations(
    val info: InfoX,
    val results: List<ResultX>
)
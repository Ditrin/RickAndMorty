package com.example.rickandmorty.data.DTOmodels

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Episodes(
    val info: InfoXX,
    val results: List<ResultXX>
)
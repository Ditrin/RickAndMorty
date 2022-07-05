package com.example.rickandmorty.data.DTOmodels

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Characters(
    val info: Info,
    val results: List<Result>
)
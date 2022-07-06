package com.example.rickandmorty.data.DTOmodels

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Info(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: Any?
)
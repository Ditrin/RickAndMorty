package com.example.rickandmorty.data.DTOmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.data.database.CharacterContract
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    val id: Int,
    val created: String,
    val episode: List<String>,
    val gender: String,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
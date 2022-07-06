package com.example.rickandmorty.data.database

object CharacterContract {
    const val TABLE_NAME = "characters"

    object Columns{
        const val ID = "id"
        const val NAME = "name"
        const val SPECIES = "species"
        const val STATUS = "status"
        const val TYPE = "type"
        const val IMAGE = "image"
        const val GENDER = "gender"
        const val LOCATION = "location"
        const val EPISODES = "episodes"
    }
}
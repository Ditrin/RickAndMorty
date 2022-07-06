package com.example.rickandmorty.data.database

object LocationContract {
    const val TABLE_NAME = "location"

    object Columns{
        const val ID = "id"
        const val CREATED = "created"
        const val DIMENSION = "dimension"
        const val NAME = "name"
        const val RESIDENTS = "residents"
        const val TYPE = "type"
        const val URL = "url"
    }
}
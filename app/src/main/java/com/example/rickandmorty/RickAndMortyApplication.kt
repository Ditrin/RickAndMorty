package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.data.database.DataBase

class RickAndMortyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DataBase.init(this)
    }
}
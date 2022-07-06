package com.example.rickandmorty.data.database

import android.content.Context
import androidx.room.Room

object DataBase {
    lateinit var db: CharacterDataBase
        private set

    fun init(context: Context) {
        db = Room.databaseBuilder(
            context,
            CharacterDataBase::class.java,
            CharacterDataBase.DB_NAME
        )
            .build()
    }
}
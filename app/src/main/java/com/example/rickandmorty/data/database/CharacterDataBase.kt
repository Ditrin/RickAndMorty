package com.example.rickandmorty.data.database

import android.os.Message
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.data.DTOmodels.Result
import com.example.rickandmorty.data.DTOmodels.SingleCharacter
import com.example.rickandmorty.data.database.datasrc.CharacterDao
import com.example.rickandmorty.data.database.datasrc.MyTypeConverter
import com.example.rickandmorty.domain.models.SingleCharacterEntity

@Database(
    entities = [
        SingleCharacterEntity::class
    ], version = CharacterDataBase.DB_VERSION
)

@TypeConverters(MyTypeConverter::class)
abstract class CharacterDataBase: RoomDatabase() {
    abstract fun CharacterDao(): CharacterDao
//    abstract fun LocationDao(): LocationDao
//    abstract fun EpisodesDao(): EpisodesDao

    companion object{
        const val DB_VERSION = 1
        const val DB_NAME = "app-database"
    }
}
package com.example.rickandmorty.data.database.datasrc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.database.CharacterContract
import com.example.rickandmorty.domain.models.SingleCharacterEntity
@Dao
interface CharacterDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: SingleCharacterEntity)

    @Query("SELECT * FROM ${CharacterContract.TABLE_NAME} WHERE id = :id")
    suspend fun getCharactersById(id: Int): SingleCharacterEntity


}
package com.example.rickandmorty.data.database.datasrc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.database.CharacterContract
import com.example.rickandmorty.data.database.LocationContract
import com.example.rickandmorty.domain.models.SingleCharacterEntity
import com.example.rickandmorty.domain.models.SingleLocationEntity

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: SingleLocationEntity)

    @Query("SELECT * FROM ${LocationContract.TABLE_NAME} WHERE id = :id")
    suspend fun getLocationById(id: Int): SingleLocationEntity
}
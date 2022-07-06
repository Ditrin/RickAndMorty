package com.example.rickandmorty.data.database.datasrc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.database.EpisodeContract
import com.example.rickandmorty.domain.models.SingleEpisodeEntity

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(episode: SingleEpisodeEntity)

    @Query("SELECT * FROM ${EpisodeContract.TABLE_NAME} WHERE id = :id")
    suspend fun getEpisodeById(id: Int): SingleEpisodeEntity
}
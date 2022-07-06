package com.example.rickandmorty.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.data.DTOmodels.SingleCharacter
import com.example.rickandmorty.data.DTOmodels.SingleEpisode
import com.example.rickandmorty.data.database.CharacterContract
import com.example.rickandmorty.data.database.EpisodeContract

@Entity(
    tableName = EpisodeContract.TABLE_NAME
)
data class SingleEpisodeEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = EpisodeContract.Columns.ID)
    val id: Int,
    @ColumnInfo(name = EpisodeContract.Columns.AIR_DATE)
    val air_date: String,
    @ColumnInfo(name = EpisodeContract.Columns.CHARACTERS)
    val characters: List<String>,
    @ColumnInfo(name = EpisodeContract.Columns.CREATED)
    val created: String,
    @ColumnInfo(name = EpisodeContract.Columns.EPISODE)
    val episode: String,
    @ColumnInfo(name = EpisodeContract.Columns.NAME)
    val name: String,
    @ColumnInfo(name = EpisodeContract.Columns.URL)
    val url: String
)
{

    fun toSingleEpisode(): SingleEpisode {
        return SingleEpisode(
            id = this.id,
            air_date = this.air_date,
            characters = this.characters,
            created = this.created,
            episode = this.episode,
            name = this.name,
            url = ""
        )
    }
}

package com.example.rickandmorty.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.data.DTOmodels.LocationX
import com.example.rickandmorty.data.DTOmodels.SingleCharacter
import com.example.rickandmorty.data.database.CharacterContract

@Entity(
    tableName = CharacterContract.TABLE_NAME
)
data class SingleCharacterEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = CharacterContract.Columns.ID)
    val id: Int,
    @ColumnInfo(name = CharacterContract.Columns.NAME)
    val name: String,
    @ColumnInfo(name = CharacterContract.Columns.SPECIES)
    val species: String,
    @ColumnInfo(name = CharacterContract.Columns.STATUS)
    val status: String,
    @ColumnInfo(name = CharacterContract.Columns.TYPE)
    val type: String,
    @ColumnInfo(name = CharacterContract.Columns.IMAGE)
    val image: String,
    @ColumnInfo(name = CharacterContract.Columns.GENDER)
    val gender: String,
    @ColumnInfo(name = CharacterContract.Columns.LOCATION)
    val location: String,
    @ColumnInfo(name = CharacterContract.Columns.EPISODES)
    val episodes: List<String>

) {

    fun toSingleCharacter(): SingleCharacter {
        return SingleCharacter(
            id = this.id,
            created = "",
            episode = this.episodes,
            gender = this.gender,
            image = this.image,
            location = LocationX(this.location, ""),
            name = this.name,
            origin = null,
            species = this.species,
            status = this.status,
            type = this.type,
            url = ""
        )
    }
}
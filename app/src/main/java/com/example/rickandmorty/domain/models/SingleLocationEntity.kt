package com.example.rickandmorty.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.data.DTOmodels.SingleLocation
import com.example.rickandmorty.data.database.LocationContract

@Entity(
    tableName = LocationContract.TABLE_NAME
)
data class SingleLocationEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = LocationContract.Columns.ID)
    val id: Int,
    @ColumnInfo(name = LocationContract.Columns.CREATED)
    val created: String,
    @ColumnInfo(name = LocationContract.Columns.DIMENSION)
    val dimension: String,
    @ColumnInfo(name = LocationContract.Columns.NAME)
    val name: String,
    @ColumnInfo(name = LocationContract.Columns.RESIDENTS)
    val residents: List<String>,
    @ColumnInfo(name = LocationContract.Columns.TYPE)
    val type: String,
    @ColumnInfo(name = LocationContract.Columns.URL)
    val url: String
) {

    fun toSingleLocation(): SingleLocation {
        return SingleLocation(
            id = this.id,
            created = "",
            dimension = this.dimension,
            name = this.name,
            residents = this.residents,
            type = this.type,
            url = ""
        )
    }
}


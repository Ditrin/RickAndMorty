package com.example.rickandmorty.domain

import com.example.rickandmorty.data.DTOmodels.LocationX
import com.example.rickandmorty.data.DTOmodels.SingleCharacter
import com.example.rickandmorty.domain.models.SingleCharacterEntity
import org.junit.Assert.*

import org.junit.Test

class CharacterMapperTest {

    @Test
    fun map() {
        val excepted = SingleCharacterEntity(
            id = 1,
            name = "Rick Sanchez",
            species = "H",
            status = "Alive",
            type = "Human",
            image = "Adelina",
            gender = "male",
            location = "Citadel",
            episodes = emptyList()
        )
        val singleChar = SingleCharacter(id = 1,
            name = "Rick Sanchez",
            species = "H",
            status = "Alive",
            type = "Human",
            image = "Adelina",
            gender = "male",
            location = LocationX("Citadel", ""),
            episode = emptyList(),
            created = "12",
            origin = null,
            url = ""
        )
        assertEquals(excepted, CharacterMapper().map(singleChar))
    }
}
package com.example.rickandmorty.domain

import com.example.rickandmorty.data.DTOmodels.SingleCharacter
import com.example.rickandmorty.domain.models.SingleCharacterEntity

class CharacterMapper : Mapper<SingleCharacter, SingleCharacterEntity> {
    override fun map(t: SingleCharacter): SingleCharacterEntity {
        return SingleCharacterEntity(
            id = t.id,
            name = t.name,
            species = t.species,
            status = t.status,
            type = t.type,
            image = t.image,
            gender = t.gender,
            location = t.location.name,
            episodes = t.episode
        )
    }
}
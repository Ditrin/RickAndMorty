package com.example.rickandmorty.domain

import com.example.rickandmorty.data.DTOmodels.SingleCharacter
import com.example.rickandmorty.data.DTOmodels.SingleLocation
import com.example.rickandmorty.domain.models.SingleCharacterEntity
import com.example.rickandmorty.domain.models.SingleLocationEntity

class LocationMapper:  Mapper<SingleLocation, SingleLocationEntity> {
    override fun map(t: SingleLocation): SingleLocationEntity {
        return SingleLocationEntity(
            id = t.id,
            created = t.created,
            dimension = t.dimension,
            name = t.name,
            residents = t.residents,
            type = t.type,
            url = t.url
        )
    }
}
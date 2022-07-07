package com.example.rickandmorty.domain

import com.example.rickandmorty.data.DTOmodels.SingleEpisode
import com.example.rickandmorty.domain.models.SingleEpisodeEntity

class EpisodeMapper : Mapper<SingleEpisode, SingleEpisodeEntity> {
    override fun map(t: SingleEpisode): SingleEpisodeEntity {
        return SingleEpisodeEntity(
            id = t.id,
            air_date = t.air_date,
            characters = t.characters,
            created = t.created,
            episode = t.episode,
            name = t.name,
            url = t.url
        )
    }
}
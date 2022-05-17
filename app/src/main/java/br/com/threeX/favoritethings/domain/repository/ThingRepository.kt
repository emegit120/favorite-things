package br.com.threeX.favoritethings.domain.repository

import br.com.threeX.favoritethings.domain.entity.Thing
import br.com.threeX.favoritethings.domain.entity.RequestState

interface ThingRepository {

    suspend fun save(thing: Thing): RequestState<Thing>

    suspend fun findBy(id: String): RequestState<Thing>
}

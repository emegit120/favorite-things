package br.com.threeX.favoritethings.data.remote.datasource

import br.com.threeX.favoritethings.domain.entity.Thing
import br.com.threeX.favoritethings.domain.entity.RequestState

interface ThingRemoteDataSource {

    suspend fun save(thing: Thing): RequestState<Thing>

    suspend fun findBy(id: String): RequestState<Thing>
}

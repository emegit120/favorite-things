package br.com.threeX.favoritethings.data.repository

import br.com.threeX.favoritethings.data.remote.datasource.ThingRemoteDataSource
import br.com.threeX.favoritethings.domain.entity.Thing
import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.repository.ThingRepository

class ThingRepositoryImpl(
    private val thingRemoteDataSource: ThingRemoteDataSource
) : ThingRepository {

    override suspend fun save(thing: Thing): RequestState<Thing> {
        return thingRemoteDataSource.save(thing)
    }

    override suspend fun findBy(id: String): RequestState<Thing> {
        return thingRemoteDataSource.findBy(id)
    }


}

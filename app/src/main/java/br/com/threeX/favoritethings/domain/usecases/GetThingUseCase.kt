package br.com.threeX.favoritethings.domain.usecases

import br.com.threeX.favoritethings.domain.entity.Thing
import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.repository.ThingRepository

class GetThingUseCase(
    private val thingRepository: ThingRepository
) {
    suspend fun findBy(id: String): RequestState<Thing> {
        return thingRepository.findBy(id)
    }
}

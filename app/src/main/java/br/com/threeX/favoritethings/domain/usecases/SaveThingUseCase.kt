package br.com.threeX.favoritethings.domain.usecases

import br.com.threeX.favoritethings.domain.entity.Thing
import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.repository.ThingRepository

class SaveThingUseCase(
    private val getUserLoggedUseCase: GetUserLoggedUseCase,
    private val thingRepository: ThingRepository
) {
    suspend fun save(thing: Thing): RequestState<Thing> {
        val userLogged = getUserLoggedUseCase.getUserLogged()

        return when(userLogged) {
            is RequestState.Success -> {
                thing.userId = userLogged.data.id
                thingRepository.save(thing)
            }

            is RequestState.Loading -> {
                RequestState.Loading
            }

            is RequestState.Error -> {
                RequestState.Error(Exception("Usuário não encontrado para associar o carro"))
            }
        }
    }

}


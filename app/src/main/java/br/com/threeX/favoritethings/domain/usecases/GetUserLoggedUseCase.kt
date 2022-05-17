package br.com.threeX.favoritethings.domain.usecases

import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.entity.User
import br.com.threeX.favoritethings.domain.repository.UserRepository

class GetUserLoggedUseCase(
    private val userRepository: UserRepository
) {

    suspend fun getUserLogged(): RequestState<User> = userRepository.getUserLogged()

}

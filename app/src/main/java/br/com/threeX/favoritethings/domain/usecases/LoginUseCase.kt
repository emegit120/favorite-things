package br.com.threeX.favoritethings.domain.usecases

import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.entity.User
import br.com.threeX.favoritethings.domain.entity.UserLogin
import br.com.threeX.favoritethings.domain.repository.UserRepository

class LoginUseCase(
    private val userRepository: UserRepository
) {

    suspend fun doLogin(userLogin: UserLogin): RequestState<User> =
        userRepository.doLogin(userLogin)

}

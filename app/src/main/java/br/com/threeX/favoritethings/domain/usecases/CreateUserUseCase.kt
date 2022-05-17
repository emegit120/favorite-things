package br.com.threeX.favoritethings.domain.usecases

import br.com.threeX.favoritethings.domain.entity.NewUser
import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.entity.User
import br.com.threeX.favoritethings.domain.repository.UserRepository

class CreateUserUseCase(
    private val userRepository: UserRepository
) {

    suspend fun create(newUser: NewUser): RequestState<User> =
        userRepository.create(newUser)
}

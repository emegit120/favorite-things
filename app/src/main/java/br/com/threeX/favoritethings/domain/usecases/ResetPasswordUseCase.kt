package br.com.threeX.favoritethings.domain.usecases

import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.repository.UserRepository

class ResetPasswordUseCase(
    private val userRepository: UserRepository
) {

    suspend fun resetPassword(email: String): RequestState<String> =
        userRepository.resetPassword(email)
}

package br.com.threeX.favoritethings.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.threeX.favoritethings.domain.usecases.CreateUserUseCase

class SignUpViewModelFactory(
    private val createUserUseCase: CreateUserUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CreateUserUseCase::class.java)
            .newInstance(createUserUseCase)
    }
}

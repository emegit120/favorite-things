package br.com.threeX.favoritethings.presentation.base.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.threeX.favoritethings.domain.usecases.GetUserLoggedUseCase

class BaseAuthViewModelFactory(
    private val getUserLoggedUseCase: GetUserLoggedUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(GetUserLoggedUseCase::class.java).newInstance(getUserLoggedUseCase)
    }
}

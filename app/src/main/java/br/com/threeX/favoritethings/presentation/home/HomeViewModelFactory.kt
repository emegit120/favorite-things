package br.com.threeX.favoritethings.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.threeX.favoritethings.domain.usecases.GetDashboardMenuUseCase
import br.com.threeX.favoritethings.domain.usecases.GetUserLoggedUseCase

class HomeViewModelFactory(
    private val getDashboardMenuUseCase: GetDashboardMenuUseCase,
    private val getUserLoggedUseCase: GetUserLoggedUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            GetDashboardMenuUseCase::class.java,
            GetUserLoggedUseCase::class.java
        )
            .newInstance(getDashboardMenuUseCase, getUserLoggedUseCase)
    }
}


package br.com.threeX.favoritethings.domain.usecases

import br.com.threeX.favoritethings.domain.entity.DashboardMenu
import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.repository.AppRepository

class GetDashboardMenuUseCase(
    private val appRespository: AppRepository
) {

    suspend fun getDashboardMenu(): RequestState<DashboardMenu> =
        appRespository.getDashboardMenu()
}

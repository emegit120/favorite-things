package br.com.threeX.favoritethings.domain.repository

import br.com.threeX.favoritethings.domain.entity.DashboardMenu
import br.com.threeX.favoritethings.domain.entity.RequestState

interface AppRepository {

    suspend fun getMinVersionApp(): RequestState<Int>

    suspend fun getDashboardMenu(): RequestState<DashboardMenu>
}

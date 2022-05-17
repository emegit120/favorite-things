package br.com.threeX.favoritethings.data.remote.datasource

import br.com.threeX.favoritethings.domain.entity.DashboardMenu
import br.com.threeX.favoritethings.domain.entity.RequestState

interface AppRemoteDataSource {

    suspend fun getMinVersionApp(): RequestState<Int>

    suspend fun getDashboardMenu(): RequestState<DashboardMenu>

}

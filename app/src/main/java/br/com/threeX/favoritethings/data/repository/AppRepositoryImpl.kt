package br.com.threeX.favoritethings.data.repository

import br.com.threeX.favoritethings.data.remote.datasource.AppRemoteDataSource
import br.com.threeX.favoritethings.domain.entity.DashboardMenu
import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.repository.AppRepository

class AppRepositoryImpl(
    private val appRemoteDataSource: AppRemoteDataSource
) : AppRepository {

    override suspend fun getMinVersionApp(): RequestState<Int> {
        return appRemoteDataSource.getMinVersionApp()
    }

    override suspend fun getDashboardMenu(): RequestState<DashboardMenu> {
        return appRemoteDataSource.getDashboardMenu()
    }

}

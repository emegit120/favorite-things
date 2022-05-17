package br.com.threeX.favoritethings.domain.usecases

import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.repository.AppRepository

class GetMinAppVersionUseCase(
    private val appRespository: AppRepository
) {

    suspend fun getMinVersionApp(): RequestState<Int> =
        appRespository.getMinVersionApp()
}

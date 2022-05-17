package br.com.threeX.favoritethings.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.threeX.favoritethings.domain.entity.DashboardItem
import br.com.threeX.favoritethings.domain.entity.DashboardMenu
import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.entity.User
import br.com.threeX.favoritethings.domain.usecases.GetDashboardMenuUseCase
import br.com.threeX.favoritethings.domain.usecases.GetUserLoggedUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getDashboardMenuUseCase: GetDashboardMenuUseCase,
    private val getUserLoggedUseCase: GetUserLoggedUseCase
) : ViewModel() {

    var dashboardItemsState = MutableLiveData<RequestState<List<DashboardItem>>>()

    var headerState = MutableLiveData<RequestState<Pair<String, String>>>()
    var userLogged: User? = null


    fun getDashboardMenu() {
        viewModelScope.launch {
            val dashResponse = getDashboardMenuUseCase.getDashboardMenu()
            val userReponse = getUserLoggedUseCase.getUserLogged()

            setUpUser(userReponse)
            setUpHeader(dashResponse, userReponse)
            setUpDashboard(dashResponse)
        }
    }

    private fun setUpUser(userResponse: RequestState<User>) {
        when(userResponse) {
            is RequestState.Success -> userLogged = userResponse.data
            else -> userLogged = null
        }
    }
    private fun setUpHeader(
        dashResponse: RequestState<DashboardMenu>,
        userResponse: RequestState<User>
    ) {

        if (dashResponse is RequestState.Success && userResponse is RequestState.Success) {
            headerState.value =
                RequestState.Success(Pair(dashResponse.data.title, userResponse.data.name))
        } else {
            headerState.value = RequestState.Error(Exception())
        }
    }

    private fun setUpDashboard(dashResponse: RequestState<DashboardMenu>) {
        when (dashResponse) {
            is RequestState.Success -> {

            }
            RequestState.Loading -> {
                dashboardItemsState.value = RequestState.Loading
            }
            is RequestState.Error -> {
                dashboardItemsState.value = RequestState.Error(dashResponse.throwable)
            }
        }
    }


}

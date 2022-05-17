package br.com.threeX.favoritethings.presentation.base.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.entity.User
import br.com.threeX.favoritethings.domain.usecases.GetUserLoggedUseCase
import kotlinx.coroutines.launch

class BaseAuthViewModel(
    private val getUserLoggedUseCase: GetUserLoggedUseCase
) : ViewModel() {


    var userLogged = MutableLiveData<RequestState<User>>()

    fun getUserLogged(){
        viewModelScope.launch {
            userLogged.value = getUserLoggedUseCase.getUserLogged()
        }
    }
}

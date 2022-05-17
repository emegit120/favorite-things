package br.com.threeX.favoritethings.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.entity.User
import br.com.threeX.favoritethings.domain.entity.UserLogin
import br.com.threeX.favoritethings.domain.usecases.LoginUseCase
import br.com.threeX.favoritethings.domain.usecases.ResetPasswordUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    val loginState = MutableLiveData<RequestState<User>>()

    val resetPasswordState = MutableLiveData<RequestState<String>>()

    fun resetPassword(email: String) {
        viewModelScope.launch {
            resetPasswordState.value = resetPasswordUseCase.resetPassword(email)
        }
    }


    fun doLogin(email: String, password: String) {
        viewModelScope.launch {
            loginState.value = loginUseCase.doLogin(UserLogin(email, password))
        }
    }
}

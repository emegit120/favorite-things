package br.com.threeX.favoritethings.presentation.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.threeX.favoritethings.domain.entity.NewUser
import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.entity.User
import br.com.threeX.favoritethings.domain.usecases.CreateUserUseCase
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    val newUserState = MutableLiveData<RequestState<User>>()

    fun create(name: String, email: String, password: String) {
        viewModelScope.launch {
            newUserState.value = createUserUseCase.create(
                NewUser(
                    name,
                    email,
                    password
                )
            )
        }
    }

}

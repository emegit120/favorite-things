package br.com.threeX.favoritethings.data.remote.datasource

import br.com.threeX.favoritethings.domain.entity.NewUser
import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.entity.User
import br.com.threeX.favoritethings.domain.entity.UserLogin

interface UserRemoteDataSource {

    suspend fun getUserLogged(): RequestState<User>

    suspend fun doLogin(userLogin: UserLogin): RequestState<User>

    suspend fun resetPassword(email: String): RequestState<String>

    suspend fun create(newUser: NewUser): RequestState<User>
}

package br.com.threeX.favoritethings.data.repository

import br.com.threeX.favoritethings.data.remote.datasource.UserRemoteDataSource
import br.com.threeX.favoritethings.domain.entity.NewUser
import br.com.threeX.favoritethings.domain.entity.RequestState
import br.com.threeX.favoritethings.domain.entity.User
import br.com.threeX.favoritethings.domain.entity.UserLogin
import br.com.threeX.favoritethings.domain.repository.UserRepository

data class UserRepositoryImpl(
    val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun getUserLogged(): RequestState<User> {
        return userRemoteDataSource.getUserLogged()
    }

    override suspend fun doLogin(userLogin: UserLogin): RequestState<User> {
        return userRemoteDataSource.doLogin(userLogin)
    }

    override suspend fun resetPassword(email: String): RequestState<String> {
        return userRemoteDataSource.resetPassword(email)
    }

    override suspend fun create(newUser: NewUser): RequestState<User> {
        return userRemoteDataSource.create(newUser)
    }

}

package br.com.threeX.favoritethings.data.remote.mapper

import br.com.threeX.favoritethings.data.remote.models.NewUserFirebasePayload
import br.com.threeX.favoritethings.domain.entity.NewUser
import br.com.threeX.favoritethings.domain.entity.User

object NewUserFirebasePayloadMapper {

    fun mapToNewUser(newUserFirebasePayload: NewUserFirebasePayload) = NewUser(
        name = newUserFirebasePayload.name ?: "",
        email = newUserFirebasePayload.email ?: "",
        password = newUserFirebasePayload.password ?: ""
    )

    fun mapToNewUserFirebasePayload(newUser: NewUser) = NewUserFirebasePayload(
        name = newUser.name,
        email = newUser.email,
        password = newUser.password
    )

    fun mapToUser(newUserFirebasePayload: NewUserFirebasePayload) = User(
        name = newUserFirebasePayload.name ?: ""
    )
}

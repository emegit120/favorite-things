package br.com.threeX.favoritethings.data.remote.datasource

import br.com.threeX.favoritethings.domain.entity.Thing
import br.com.threeX.favoritethings.domain.entity.RequestState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ThingRemoteDataSourceImpl(
    private val firebaseFirestore: FirebaseFirestore
) : ThingRemoteDataSource {

    override suspend fun save(thing: Thing): RequestState<Thing> {
        return try {
            firebaseFirestore.collection("cars")
                .document(thing.userId)
                .set(thing)
                .await()
            RequestState.Success(thing)
        } catch (e: Exception) {
            RequestState.Error(e)
        }
    }

    override suspend fun findBy(id: String): RequestState<Thing> {
        return try {
            val thing = firebaseFirestore.collection("cars")
                .document(id)
                .get()
                .await().toObject(Thing::class.java) ?: Thing()

            RequestState.Success(thing)

        } catch (e: Exception) {
            RequestState.Error(e)
        }
    }

}

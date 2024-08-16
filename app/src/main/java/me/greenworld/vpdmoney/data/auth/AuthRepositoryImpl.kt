package me.greenworld.vpdmoney.data.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import me.greenworld.vpdmoney.data.di.IoDispatcher
import me.greenworld.vpdmoney.domain.auth.AuthRepository
import me.greenworld.vpdmoney.domain.common.Resource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): AuthRepository {

    override suspend fun login(email: String, password: String): Resource<Unit> =
        withContext(dispatcher){
/*
           val result = firebaseAuth.signInWithEmailAndPassword(email, password)
            if (result.isSuccessful){
                return@withContext Resource.success(Unit)
            }else{
                return@withContext Resource.error("Unable to login user")
            }*/

                try {
                    // Await the result of signInWithEmailAndPassword
                    firebaseAuth.signInWithEmailAndPassword(email, password).await()
                    Resource.success(Unit)
                } catch (e: Exception) {
                    // Catch any exceptions and return an error Resource
                    Resource.error("Unable to login user: ${e.message}")
                }
        }


    override suspend fun logout(): Resource<Unit> {
       return Resource.success(firebaseAuth.signOut())
    }
}
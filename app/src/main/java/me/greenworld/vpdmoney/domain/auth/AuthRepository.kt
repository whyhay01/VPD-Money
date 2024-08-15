package me.greenworld.vpdmoney.domain.auth

import me.greenworld.vpdmoney.domain.common.Resource

interface AuthRepository {

   suspend fun login(email: String, password: String) : Resource<Unit>

    suspend fun logout(): Resource<Unit>
}
package me.greenworld.vpdmoney.domain.auth

import me.greenworld.vpdmoney.domain.common.Resource
import me.greenworld.vpdmoney.domain.common.usecase.SuspendUseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) : SuspendUseCase<LoginParams,Resource<Unit>>() {
    override suspend fun invoke(param: LoginParams): Resource<Unit> =
        authRepository.login(param.email, param.password)
}

data class LoginParams(val email: String, val password: String)
package me.greenworld.vpdmoney.domain.account

import me.greenworld.vpdmoney.domain.common.usecase.SuspendUseCase
import me.greenworld.vpdmoney.domain.model.UserAccount
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(private val accountRepository: AccountRepository): SuspendUseCase<String, UserAccount?>() {
    override suspend fun invoke(param: String): UserAccount? {
        return accountRepository.getAccount(param)
    }
}
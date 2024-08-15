package me.greenworld.vpdmoney.domain.account

import me.greenworld.vpdmoney.domain.common.usecase.SuspendUseCase
import me.greenworld.vpdmoney.domain.common.usecase.UseCase
import me.greenworld.vpdmoney.domain.model.UserAccount
import javax.inject.Inject

class GetAccountsUseCase @Inject constructor(private val accountRepository: AccountRepository):SuspendUseCase<Unit, List<UserAccount>>() {
    override suspend fun invoke(param: Unit): List<UserAccount> {
        return accountRepository.getAccounts()
    }
}
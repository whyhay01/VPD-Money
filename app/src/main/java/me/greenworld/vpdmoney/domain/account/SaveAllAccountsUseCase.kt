package me.greenworld.vpdmoney.domain.account

import me.greenworld.vpdmoney.domain.common.usecase.SuspendUseCase
import me.greenworld.vpdmoney.domain.model.UserAccount
import javax.inject.Inject

class SaveAllAccountsUseCase @Inject constructor(private val accountRepository: AccountRepository) :
    SuspendUseCase<Unit, Unit>() {
    override suspend fun invoke(param: Unit) {
        accountRepository.saveAllAccount()

    }
}
package me.greenworld.vpdmoney.domain.account

import me.greenworld.vpdmoney.domain.common.usecase.SuspendUseCase
import me.greenworld.vpdmoney.domain.model.UserAccount
import javax.inject.Inject

class UpdateAccountUseCase @Inject constructor(private val accountRepository: AccountRepository) :
    SuspendUseCase<UpdateAccountParam, Int>() {
    override suspend fun invoke(param: UpdateAccountParam): Int {
        return accountRepository.updateAccount(
            accountNumber = param.accountNumber,
            balance = param.balance
        )
    }
}

data class UpdateAccountParam(val accountNumber: String, val balance: Int)
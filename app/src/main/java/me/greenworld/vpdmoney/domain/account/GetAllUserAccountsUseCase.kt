package me.greenworld.vpdmoney.domain.account

import kotlinx.coroutines.flow.Flow
import me.greenworld.vpdmoney.domain.common.usecase.SuspendUseCase
import me.greenworld.vpdmoney.domain.common.usecase.UseCase
import me.greenworld.vpdmoney.domain.model.UserAccount
import javax.inject.Inject

class GetAllUserAccountsUseCase @Inject constructor(private val accountRepository: AccountRepository):
    UseCase<Unit, Flow<List<UserAccount>>>() {
    override fun invoke(param: Unit): Flow<List<UserAccount>> {
        return accountRepository.accounts()
    }
}
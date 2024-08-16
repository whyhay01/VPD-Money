package me.greenworld.vpdmoney.data.account

import me.greenworld.vpdmoney.common.Mapper
import me.greenworld.vpdmoney.domain.model.UserAccount
import javax.inject.Inject

class AccountEntityMapper @Inject constructor() : Mapper<AccountEntity, UserAccount> {
    override fun from(cache: AccountEntity): UserAccount =
        cache.run {
            UserAccount(
                fullName = this.fullName,
                phoneNumber = this.phoneNumber,
                accountNumber = this.accountNumber,
                balance = this.balance,
                pin = this.pin
            )
        }

    override fun to(data: UserAccount): AccountEntity =
        data.run {
            AccountEntity(
                fullName = fullName,
                phoneNumber = phoneNumber,
                accountNumber = accountNumber,
                balance = balance,
                pin = pin
            )
        }

}
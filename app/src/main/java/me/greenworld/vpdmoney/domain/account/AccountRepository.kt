package me.greenworld.vpdmoney.domain.account

import kotlinx.coroutines.flow.Flow
import me.greenworld.vpdmoney.domain.model.UserAccount

interface AccountRepository {

    fun accounts(): Flow<List<UserAccount>>

    suspend fun getAccounts():List<UserAccount>

    suspend fun getAccount(accountNumber: String): UserAccount?

    suspend fun updateAccount(accountNumber: String, balance: Int): Int

    suspend fun saveAllAccount()

}
package me.greenworld.vpdmoney.data.account

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.greenworld.vpdmoney.data.di.IoDispatcher
import me.greenworld.vpdmoney.domain.account.AccountRepository
import me.greenworld.vpdmoney.domain.model.UserAccount
import me.greenworld.vpdmoney.domain.model.userAccounts
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val mapper: AccountEntityMapper,
    private val accountDao: AccountDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : AccountRepository {
    override fun accounts(): Flow<List<UserAccount>> {
        return accountDao.getAllAccounts().map {
            mapper.mapModelList(it)
        }

    }


    override suspend fun getAccounts(): List<UserAccount> {
        return accountDao.getAllAccount().map {
            mapper.from(it)
        }
    }

    override suspend fun getAccount(accountNumber: String): UserAccount? =
        withContext(dispatcher) {
            accountDao.getAccount(accountNumber)?.let {
                mapper.from(it)
            }

        }

    override suspend fun updateAccount(accountNumber: String, balance: Int): Int =
        withContext(dispatcher) {
            accountDao.updateAccount(accountNumber = accountNumber, balance = balance)

        }

    override suspend fun saveAllAccount() {
        withContext(dispatcher) {
            val accounts = accountDao.getAllAccount()
            if (accounts.isEmpty()) {
                accountDao.saveAllAccounts(userAccounts.map {
                    mapper.to(it)
                })
            }
        }
    }


}
package me.greenworld.vpdmoney.data.account

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllAccounts(accounts: List<AccountEntity>): List<Long>

    @Query("SELECT * FROM account")
    fun getAllAccounts(): Flow<List<AccountEntity>>

    @Query("SELECT * FROM account")
    fun getAllAccount(): List<AccountEntity>

    @Query("SELECT * FROM account WHERE accountNumber = :accountNumber")
    fun getAccount(accountNumber: String) : AccountEntity?

    @Query("UPDATE account SET balance = :balance WHERE accountNumber = :accountNumber")
    fun updateAccount(balance:Int, accountNumber: String): Int
}
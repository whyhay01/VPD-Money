package me.greenworld.vpdmoney.cache.transaction

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTransaction(transactionEntity: TransactionEntity): Long

    @Query("SELECT * FROM `transaction` WHERE transactionID = :transactionID")
    fun getTransaction(transactionID: String): TransactionEntity

    @Query("SELECT * FROM `transaction`")
    fun getAllTransactions(): Flow<List<TransactionEntity>>
}
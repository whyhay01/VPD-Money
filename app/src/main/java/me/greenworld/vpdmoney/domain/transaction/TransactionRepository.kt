package me.greenworld.vpdmoney.domain.transaction

import kotlinx.coroutines.flow.Flow
import me.greenworld.vpdmoney.domain.model.Transaction

interface TransactionRepository {

    suspend fun saveTransaction(transaction: Transaction)

     suspend fun getTransaction(transactionID: String): Transaction

     fun getAllTransactions(): Flow<List<Transaction>>
}
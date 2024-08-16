package me.greenworld.vpdmoney.data.transaction

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.greenworld.vpdmoney.data.di.IoDispatcher
import me.greenworld.vpdmoney.domain.model.Transaction
import me.greenworld.vpdmoney.domain.transaction.TransactionRepository
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val transactionDao: TransactionDao,
    private val mapper: TransactionEntityMapper
) : TransactionRepository {
    override suspend fun saveTransaction(transaction: Transaction) {
        withContext(dispatcher) {
            transactionDao.saveTransaction(mapper.to(transaction))
        }
    }

    override suspend fun getTransaction(transactionID: String): Transaction {
        return mapper.from(transactionDao.getTransaction(transactionID))
    }

    override fun getAllTransactions(): Flow<List<Transaction>> {
        return transactionDao.getAllTransactions().map {
            mapper.mapModelList(it)
        }
    }
}
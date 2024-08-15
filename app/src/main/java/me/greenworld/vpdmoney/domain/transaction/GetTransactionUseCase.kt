package me.greenworld.vpdmoney.domain.transaction

import me.greenworld.vpdmoney.domain.common.usecase.SuspendUseCase
import me.greenworld.vpdmoney.domain.model.Transaction
import javax.inject.Inject

class GetTransactionUseCase @Inject constructor(private val repository: TransactionRepository): SuspendUseCase<String, Transaction>() {
    override suspend fun invoke(param: String): Transaction {
        return repository.getTransaction(param)
    }
}
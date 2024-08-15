package me.greenworld.vpdmoney.domain.transaction

import me.greenworld.vpdmoney.domain.common.usecase.SuspendUseCase
import me.greenworld.vpdmoney.domain.model.Transaction
import javax.inject.Inject

class SaveTransactionUseCase @Inject constructor(private val repository: TransactionRepository): SuspendUseCase<Transaction, Unit>() {
    override suspend fun invoke(param: Transaction) {
        repository.saveTransaction(param)
    }
}
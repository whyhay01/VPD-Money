package me.greenworld.vpdmoney.domain.transaction

import kotlinx.coroutines.flow.Flow
import me.greenworld.vpdmoney.domain.common.usecase.UseCase
import me.greenworld.vpdmoney.domain.model.Transaction
import javax.inject.Inject

class GetAllTransactionsUseCase @Inject constructor(private val repository: TransactionRepository): UseCase<Unit, Flow<List<Transaction>>>() {
    override fun invoke(param: Unit): Flow<List<Transaction>> {
        return repository.getAllTransactions()
    }
}
package me.greenworld.vpdmoney.data.transaction

import me.greenworld.vpdmoney.common.Mapper
import me.greenworld.vpdmoney.domain.model.Transaction
import javax.inject.Inject

class TransactionEntityMapper @Inject constructor() : Mapper<TransactionEntity, Transaction> {
    override fun from(cache: TransactionEntity): Transaction =
        cache.run {
            Transaction(
                transactionID,
                sourceAccountNumber,
                destinationAccountNumber,
                amountTransferred,
                beneficiaryName,
                date,
                balance
            )
        }


    override fun to(data: Transaction): TransactionEntity =
        data.run {
            TransactionEntity(
                transactionID,
                sourceAccountNumber,
                destinationAccountNumber,
                amountTransferred,
                beneficiaryName,
                this.date,
                balance
            )
        }
}
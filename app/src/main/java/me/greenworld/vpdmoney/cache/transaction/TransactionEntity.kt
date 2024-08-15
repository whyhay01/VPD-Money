package me.greenworld.vpdmoney.cache.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey
    val transactionID: String,
    val sourceAccountNumber: String,
    val destinationAccountNumber: String,
    val amountTransferred: Int,
    val beneficiaryName: String,
    val date:Long,
    val balance: Int
)

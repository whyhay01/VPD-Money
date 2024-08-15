package me.greenworld.vpdmoney.domain.model

data class Transaction(
    val transactionID: String,
    val sourceAccountNumber: String,
    val destinationAccountNumber: String,
    val amountTransferred: Int,
    val beneficiaryName: String,
    val date:Long,
    val balance: Int
    )

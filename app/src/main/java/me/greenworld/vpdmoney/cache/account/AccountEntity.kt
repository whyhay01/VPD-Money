package me.greenworld.vpdmoney.cache.account

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class AccountEntity(
    val fullName: String,
    val phoneNumber: String,
    @PrimaryKey
    val accountNumber: String,
    val balance: Int,
    val pin: String
)

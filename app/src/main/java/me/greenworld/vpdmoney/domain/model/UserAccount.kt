package me.greenworld.vpdmoney.domain.model

import androidx.room.PrimaryKey

data class UserAccount(
    val fullName: String,
    val phoneNumber: String,
    val accountNumber: String,
    val balance: Int = 90_000,
    val pin: String
)


val userAccounts = listOf(
    UserAccount(
        fullName = "Adebowale Haruna",
        phoneNumber = "8123456790",
        accountNumber = "1234",
        balance = 100_000,
        pin = ""
    ),
    UserAccount(
        fullName = "Akanbi Ademola",
        phoneNumber = "8122222222",
        accountNumber = "0987",
        balance = 95_000,
        pin = ""
    ),
    UserAccount(
        fullName = "Tinubu Ahmed",
        phoneNumber = "8133333333",
        accountNumber = "5678",
        pin = ""
    ),
    UserAccount(
        fullName = "Oluwole Oluwafemi",
        phoneNumber = "8144444444",
        accountNumber = "4321",
        balance = 90_000,
        pin = ""
    ),
    UserAccount(
        fullName = "Awoda Pelumi",
        phoneNumber = "8155555555",
        accountNumber = "8765",
        balance = 85_000,
        pin = ""
    ),
    UserAccount(
        fullName = "Alabi John",
        phoneNumber = "8166666666",
        accountNumber = "7890",
        balance = 80_000,
        pin = ""
    ),
    UserAccount(
        fullName = "Ayanwoye Kunle",
        phoneNumber = "8177777777",
        accountNumber = "3456",
        75_000,
        pin = ""
    )
)

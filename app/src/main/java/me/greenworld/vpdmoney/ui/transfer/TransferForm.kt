package me.greenworld.vpdmoney.ui.transfer

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData

data class TransferForm(
    var accountNumber: String = "",
    var amount: String = ""
)

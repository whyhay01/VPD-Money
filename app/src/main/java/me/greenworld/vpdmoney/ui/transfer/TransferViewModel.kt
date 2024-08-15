package me.greenworld.vpdmoney.ui.transfer

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.greenworld.vpdmoney.R
import me.greenworld.vpdmoney.common.Event
import me.greenworld.vpdmoney.common.generateId
import me.greenworld.vpdmoney.domain.account.GetAccountUseCase
import me.greenworld.vpdmoney.domain.account.UpdateAccountParam
import me.greenworld.vpdmoney.domain.account.UpdateAccountUseCase
import me.greenworld.vpdmoney.domain.model.Transaction
import me.greenworld.vpdmoney.domain.model.UserAccount
import me.greenworld.vpdmoney.domain.transaction.SaveTransactionUseCase
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val updateAccountUseCase: UpdateAccountUseCase,
    private val saveTransactionUseCase: SaveTransactionUseCase,
    private val getAccountUseCase: GetAccountUseCase
): ViewModel() {

    private val _error = MutableLiveData<Triple<Boolean, String, Boolean>>()
    val error: LiveData<Triple<Boolean, String, Boolean>> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _showConfirmDialog = MutableLiveData<Boolean>()
    val showConfirmDialog: LiveData<Boolean> = _showConfirmDialog

    private val _success = MutableLiveData<Pair<Boolean, String>>()
    val success: LiveData<Pair<Boolean, String>> = _success

    val accountNumberError = MutableLiveData<Event<@StringRes Int>>()
    val amountError = MutableLiveData<Event<@StringRes Int>>()

    var form = TransferForm()

    private val transactionID = generateId()


    fun transfer(userAccount: UserAccount){
        //Does account exit
        viewModelScope.launch {
            _loading.value = true
            delay(2000L)
            val beneficiaryAccount = getAccountUseCase(form.accountNumber)
            if (beneficiaryAccount == null){
                _loading.value = false
                _error.value = Triple(true, "Account number does not exist", true)
            }else{
                if (userAccount.balance < form.amount.toInt() || userAccount.balance == 0){
                    _error.value = Triple(first = true, second = "Insufficient balance", third = false)
                    _loading.value = false
                }else{
                    //make the transfer
                   val beneficiaryNewBalance =  beneficiaryAccount.balance + form.amount.toInt()
                    val giverNewBalance = userAccount.balance - form.amount.toInt()
                    updateAccountUseCase(UpdateAccountParam(beneficiaryAccount.accountNumber,beneficiaryNewBalance ))
                    updateAccountUseCase(UpdateAccountParam(userAccount.accountNumber, giverNewBalance))
                    saveTransactionUseCase(Transaction(
                        transactionID = transactionID,
                        sourceAccountNumber = userAccount.accountNumber,
                        destinationAccountNumber = beneficiaryAccount.accountNumber,
                        amountTransferred = form.amount.toInt(),
                        beneficiaryName = beneficiaryAccount.fullName,
                        date = Date().time,
                        balance = giverNewBalance
                    ))

                    _loading.value = false
                    _success.value = Pair(first = true, second = "Transfer successful")
                }
            }
        }
    }

    fun onTransferButtonClicked(){
        if (isFormFilled(form)){
            _showConfirmDialog.value = true
        }else{
            postLoginForm(form)
        }
    }




    private fun isFormFilled(transferForm: TransferForm): Boolean =
        transferForm.accountNumber.isNotEmpty() && transferForm.amount.isNotEmpty()

    private fun postLoginForm(transferForm: TransferForm) {
        with(transferForm) {
            when {
                this.accountNumber.isEmpty() -> accountNumberError.value = Event(R.string.account_number_error)
                this.amount.isEmpty() -> amountError.value = Event(R.string.amount_error)

            }
        }
    }
}
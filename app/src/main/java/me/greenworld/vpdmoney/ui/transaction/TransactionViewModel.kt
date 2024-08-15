package me.greenworld.vpdmoney.ui.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import me.greenworld.vpdmoney.domain.transaction.GetAllTransactionsUseCase
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase
):ViewModel() {

    private val _display = MutableLiveData<Boolean>()
    val display: LiveData<Boolean> = _display
    val transactions = getAllTransactionsUseCase(Unit).asLiveData(viewModelScope.coroutineContext)
}
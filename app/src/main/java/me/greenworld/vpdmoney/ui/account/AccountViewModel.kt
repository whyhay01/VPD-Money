package me.greenworld.vpdmoney.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.greenworld.vpdmoney.domain.account.GetAllUserAccountsUseCase
import me.greenworld.vpdmoney.domain.account.SaveAllAccountsUseCase
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getAllUserAccountsUseCase: GetAllUserAccountsUseCase,
    private val saveAllAccountsUseCase: SaveAllAccountsUseCase
): ViewModel() {

    init {
        viewModelScope.launch {
            saveAllAccountsUseCase(Unit)
        }
    }
    val account = getAllUserAccountsUseCase(Unit).asLiveData(viewModelScope.coroutineContext)
}
package me.greenworld.vpdmoney.ui.auth

import android.util.Patterns
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.greenworld.vpdmoney.R
import me.greenworld.vpdmoney.common.Event
import me.greenworld.vpdmoney.domain.auth.LoginParams
import me.greenworld.vpdmoney.domain.auth.LoginUseCase
import me.greenworld.vpdmoney.domain.network.CheckInternetUseCase
import me.greenworld.vpdmoney.ui.transfer.TransferForm
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val checkNetworkUseCase: CheckInternetUseCase
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Pair<Boolean, String>>()
    val error: LiveData<Pair<Boolean, String>> = _error

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    private val _checkInternet = MutableLiveData<Boolean>()
    val checkInternet: LiveData<Boolean> = _checkInternet

    var form = LoginForm()

    val emailError = MutableLiveData<Event<@StringRes Int>>()
    val passwordError = MutableLiveData<Event<@StringRes Int>>()

    init {
        _loading.value = false
        _checkInternet.value = checkNetworkUseCase(Unit)
    }

    private fun login(){
        viewModelScope.launch {
           if(!checkNetworkUseCase(Unit)){
               _error.value = Pair(first = true, second = "You are not connected to the internet")
           }else{
               _loading.value = true
               val result = loginUseCase(LoginParams(email = form.email, password = form.password))
               if (result.isSuccess()){
                   _loading.value = false
                   _success.value = true
               }else{
                   _loading.value = false
                   _error.value = Pair(first = true, second = result.message)
               }
           }
        }
    }

    fun onLoginClicked(){
        if (isFormFilled(form)){
            login()
        }else{
            postLoginForm(form)
        }
    }


    private fun isFormFilled(loginForm: LoginForm): Boolean =
        loginForm.email.isNotEmpty() && loginForm.password.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(
            loginForm.email
        ).matches()

    private fun postLoginForm(loginForm: LoginForm) {
        with(loginForm) {
            when {
                this.email.isEmpty() -> emailError.value = Event(R.string.email_error_empty)
                this.password.isEmpty() -> passwordError.value = Event(R.string.password_error)
                !Patterns.EMAIL_ADDRESS.matcher(this.email).matches() -> emailError.value =
                    Event(R.string.email_error_not_match)

            }
        }
    }
}
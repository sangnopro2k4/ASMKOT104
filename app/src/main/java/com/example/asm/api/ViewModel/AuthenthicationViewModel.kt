package com.example.asm.api.ViewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asm.api.LoginRequest
import com.example.asm.api.LoginResponse
import com.example.asm.api.RegisterRequest
import com.example.asm.api.RetrofitInstance
import kotlinx.coroutines.launch

class AuthenticationViewModel : ViewModel() {
    private var _loginResponse = mutableStateOf<LoginResponse?>(null)
    private var _registerResponse = mutableStateOf<LoginResponse?>(null)
    private var _loginErrorMessage = mutableStateOf("")
    private var _registerErrorMessage = mutableStateOf("")
    var loginErrorMessage: State<String> = _loginErrorMessage
    var registerErrorMessage: State<String> = _registerErrorMessage

    var loginResponse: State<LoginResponse?> = _loginResponse
    var registerResponse: State<LoginResponse?> = _registerResponse

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.login(loginRequest = LoginRequest(email, password))
                _loginResponse.value = response
            } catch (e: Exception) {
                _loginErrorMessage.value = "Login failed email or password doesn\'t match"
                e.printStackTrace()
            }
        }
    }

    fun register(email: String, password: String, fullname: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.register(registerRequest = RegisterRequest(email, password, fullname))
                _registerResponse.value = response
            } catch (e: Exception) {
                _registerErrorMessage.value = "Register failed"
                Log.e("Err", "register: failed", )
                e.printStackTrace()
            }
        }
    }
}
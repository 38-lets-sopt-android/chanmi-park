package com.example.letssopt.presentation.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.data.local.UserPreferences
import com.example.letssopt.presentation.login.uistate.LoginUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class LoginEvent {
    object NavigateToMain : LoginEvent()
    data class ShowToast(val message: String) : LoginEvent()
}

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application)

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _event = Channel<LoginEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    init {
        if (userPreferences.isLoggedIn()) {
            viewModelScope.launch {
                _event.send(LoginEvent.NavigateToMain)
            }
        }
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email, isLoginEnabled = email.isNotBlank() && it.password.isNotBlank()) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password, isLoginEnabled = it.email.isNotBlank() && password.isNotBlank()) }
    }

    fun login() {
        val savedId = userPreferences.getUserId()
        val savedPw = userPreferences.getUserPw()

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when {
                savedId == null || savedPw == null -> {
                    _event.send(LoginEvent.ShowToast("회원가입을 먼저 해주세요"))
                }
                _uiState.value.email == savedId && _uiState.value.password == savedPw -> {
                    userPreferences.saveLoginState(true)
                    _event.send(LoginEvent.NavigateToMain)
                }
                else -> {
                    _event.send(LoginEvent.ShowToast("아이디 또는 비밀번호가 일치하지 않습니다"))
                }
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun saveSignUpInfo(userId: String, userPw: String) {
        userPreferences.saveSignUpInfo(userId, userPw)
    }
}

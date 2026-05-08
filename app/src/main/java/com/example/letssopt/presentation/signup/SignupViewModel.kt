package com.example.letssopt.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.data.dto.signup.SignUpRequest
import com.example.letssopt.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class SignUpUiState {
    object Idle : SignUpUiState()
    object Loading : SignUpUiState()
    object Success : SignUpUiState()
    data class Error(val message: String) : SignUpUiState()
}

class SignUpViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<SignUpUiState>(SignUpUiState.Idle)
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun resetState() {
        _uiState.value = SignUpUiState.Idle
    }

    fun signUp(
        loginId: String,
        password: String,
        name: String,
        email: String,
        age: Int,
        part: String
    ) = viewModelScope.launch {
        _uiState.value = SignUpUiState.Loading

        runCatching {
            RetrofitClient.apiService.signUp(
                SignUpRequest(loginId, password, name, email, age, part)
            )
        }.onSuccess { response ->
            if (response.isSuccessful) {
                _uiState.value = SignUpUiState.Success
            } else {
                val message = response.body()?.message ?: "회원가입에 실패했습니다"
                _uiState.value = SignUpUiState.Error(message)
            }
        }.onFailure { e ->
            _uiState.value = SignUpUiState.Error(e.message ?: "네트워크 오류가 발생했습니다")
        }
    }
}

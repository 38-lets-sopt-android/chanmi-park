package com.example.letssopt.presentation.login.uistate

data class LoginUiState(
    val id: String = "",
    val password: String = "",
    val isLoginEnabled: Boolean = false,
    val isLoading: Boolean = false,
)

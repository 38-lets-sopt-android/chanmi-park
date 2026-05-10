package com.example.letssopt.presentation.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.data.local.UserPreferences
import com.example.letssopt.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProfileUiState(
    val loginId: String = "",
    val name: String = "",
    val email: String = "",
    val age: Int = 0,
    val part: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferences = UserPreferences(application)

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        fetchProfile()
    }

    private fun fetchProfile() {
        val loginId = userPreferences.getUserId() ?: return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            runCatching {
                RetrofitClient.apiService.profile(loginId)
            }.onSuccess { response ->
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        _uiState.value = ProfileUiState(
                            loginId = data.loginId,
                            name = data.name,
                            email = data.email,
                            age = data.age,
                            part = data.part,
                        )
                    }
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "프로필을 불러오지 못했습니다"
                    )
                }
            }.onFailure {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "네트워크 오류가 발생했습니다"
                )
            }
        }
    }
}

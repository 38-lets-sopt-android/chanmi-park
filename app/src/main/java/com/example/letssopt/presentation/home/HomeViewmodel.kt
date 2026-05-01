package com.example.letssopt.presentation.home

import androidx.lifecycle.ViewModel
import com.example.letssopt.presentation.home.fake.FakeHomeRepository
import com.example.letssopt.presentation.home.uistate.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = HomeUiState(
            newContents = FakeHomeRepository.newContents,
            whatgorismContents = FakeHomeRepository.whatgorismContents,
            upcomingContents = FakeHomeRepository.upcomingContents,
            partyContents = FakeHomeRepository.partyContents,
        )
    }
}
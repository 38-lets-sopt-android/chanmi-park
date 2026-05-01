package com.example.letssopt.presentation.home.uistate

import com.example.letssopt.domain.model.ContentModel
import com.example.letssopt.domain.model.PartyModel

data class HomeUiState(
    val newContents: List<ContentModel> = emptyList(),
    val whatgorismContents: List<ContentModel> = emptyList(),
    val upcomingContents: List<ContentModel> = emptyList(),
    val partyContents: List<PartyModel> = emptyList(),
)
package com.example.letssopt.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class PartyModel(
    val id: Int,
    val title: String,
    val image: Int,
    val startTime: String,
    val tag: String,
)
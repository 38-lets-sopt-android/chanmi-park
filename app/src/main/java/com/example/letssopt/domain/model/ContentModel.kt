package com.example.letssopt.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class ContentModel(
    val id: Int,
    val title: String,
    val image: Int,
)
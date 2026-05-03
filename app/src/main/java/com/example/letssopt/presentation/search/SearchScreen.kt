package com.example.letssopt.presentation.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
data object Search {}

@Composable
fun SearchRoute(
    paddingValues: PaddingValues,
){
    SearchScreen(modifier = Modifier.padding(paddingValues))
}

@Composable
private fun SearchScreen(
    modifier: Modifier = Modifier
){
    Text(
        "SearchScreen"
    )
}
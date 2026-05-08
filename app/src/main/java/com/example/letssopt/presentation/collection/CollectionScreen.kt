package com.example.letssopt.presentation.collection

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
data object Collection {}

@Composable
fun CollectionRoute(
    paddingValues: PaddingValues,
){
    CollectionScreen(modifier = Modifier.padding(paddingValues))
}

@Composable
private fun CollectionScreen(
    modifier: Modifier = Modifier
){
    Text(
        "CollectionScreen"
    )
}
package com.example.letssopt.presentation.purchase

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
data object Purchase {}

@Composable
fun PurchaseRoute(
    paddingValues: PaddingValues,
){
    PurchaseScreen(modifier = Modifier.padding(paddingValues))
}

@Composable
private fun PurchaseScreen(
    modifier: Modifier = Modifier
){
    Text(
        "PurchaseScreen"
    )
}
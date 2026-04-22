package com.example.letssopt.presentation.purchase

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PurchaseRoute(
    modifier: Modifier = Modifier,
){
    PurchaseScreen()
}

@Composable
private fun PurchaseScreen(){
    Text(
        "PurchaseScreen"
    )
}
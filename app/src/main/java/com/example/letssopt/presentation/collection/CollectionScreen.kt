package com.example.letssopt.presentation.collection

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CollectionRoute(
    modifier: Modifier = Modifier,
){
    CollectionScreen()
}

@Composable
private fun CollectionScreen(){
    Text(
        "CollectionScreen"
    )
}
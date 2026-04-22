package com.example.letssopt.presentation.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
){
    HomeScreen()
}

@Composable
private fun HomeScreen(){
    Text(
        "homeScreen"
    )
}
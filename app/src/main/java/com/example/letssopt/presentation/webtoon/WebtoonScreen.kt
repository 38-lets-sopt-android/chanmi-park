package com.example.letssopt.presentation.webtoon

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WebtoonRoute(
    modifier: Modifier = Modifier,
){
    WebtoonScreen()
}

@Composable
private fun WebtoonScreen(){
    Text(
        "WebtoonScreen"
    )
}
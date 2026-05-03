package com.example.letssopt.presentation.webtoon

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
data object Webtoon {}

@Composable
fun WebtoonRoute(
    paddingValues: PaddingValues,
){
    WebtoonScreen(modifier = Modifier.padding(paddingValues))
}

@Composable
private fun WebtoonScreen(
    modifier: Modifier = Modifier
){
    Text(
        "WebtoonScreen"
    )
}
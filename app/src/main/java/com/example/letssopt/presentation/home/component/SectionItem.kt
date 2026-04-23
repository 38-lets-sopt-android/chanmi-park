package com.example.letssopt.presentation.home.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SectionItem(
    @DrawableRes contentImg: Int,
    contentTitle: String,
    width: Dp = 100.dp,
    height: Dp = 150.dp,
    modifier: Modifier = Modifier
){
    Image(
        painter = painterResource(id = contentImg),
        contentDescription = contentTitle,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(width = width, height = height)
            .clip(RoundedCornerShape(10.dp)),
    )
}
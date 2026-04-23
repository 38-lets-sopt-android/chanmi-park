package com.example.letssopt.presentation.home.component

import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.LetsTheme
import com.example.letssopt.presentation.home.ContentItem

@Composable
fun WhatgorismSection(
    contents: List<ContentItem>,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_logo_whatgorism_80),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 16.dp),
            tint = LetsTheme.colors.textPrimary,
        )

        Spacer(modifier = Modifier.height(4.dp))

        SectionTitle(
            title = "예능부터 드라마까지!",
            modifier = Modifier.padding(horizontal = 16.dp),
            showMore = true,
            titleColor = LetsTheme.colors.textSecondary,
        )

        Spacer(modifier = Modifier.height(6.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(13.dp),
        ){
            items(contents) { content ->
                SectionItem(
                    contentImg = content.image,
                    contentTitle = content.title,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewNewContent(){
    LetsTheme {
        WhatgorismSection(
            contents = listOf(
                ContentItem(id = 1, title = "테스트1", image = R.drawable.img_content_7),
                ContentItem(id = 2, title = "테스트2", image = R.drawable.img_content_8),
                ContentItem(id = 3, title = "테스트3", image = R.drawable.img_content_9),
                ContentItem(id = 3, title = "테스트3", image = R.drawable.img_content_9),
            )
        )
    }
}
package com.example.letssopt.presentation.home.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.LetsTheme
import com.example.letssopt.presentation.home.ContentItem
import com.example.letssopt.presentation.home.ContentSection

@Composable
fun NewContentSection(
    contents: List<ContentItem>,
    modifier: Modifier = Modifier
){
    LazyRow (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ){
        items(contents) { content ->
            NewContentItem(
                contentImg = content.image,
                contentTitle = content.title,
            )
        }
    }
}

@Composable
private fun NewContentTitle(
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier,
    ){
        Text(
            text = "방금 막 도착한 신상 컨텐츠",
            color = LetsTheme.colors.textPrimary,
            style = LetsTheme.typography.title.bold_20,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "예능부터 드라마까지!",
            color = LetsTheme.colors.textSecondary,
            style = LetsTheme.typography.subtitle.body_16,
        )
    }
}

@Composable
private fun NewContentItem(
    @DrawableRes contentImg: Int,
    contentTitle: String,
    modifier: Modifier = Modifier
){
    Image(
        painter = painterResource(id = contentImg),
        contentDescription = contentTitle,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(height = 160.dp, width = 280.dp)
            .clip(RoundedCornerShape(10.dp)),
    )
}

@Preview
@Composable
private fun PreviewNewContent(){
    LetsTheme {
        NewContentSection(
            contents = listOf(
                ContentItem(id = 1, title = "테스트1", image = R.drawable.img_content_7),
                ContentItem(id = 2, title = "테스트2", image = R.drawable.img_content_8),
                ContentItem(id = 3, title = "테스트3", image = R.drawable.img_content_9),
            )
        )
    }
}
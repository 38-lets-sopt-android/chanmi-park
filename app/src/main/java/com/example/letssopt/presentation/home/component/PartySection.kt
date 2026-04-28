package com.example.letssopt.presentation.home.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.LetsTheme
import com.example.letssopt.presentation.home.ContentItem

@Composable
fun PartySection(
    contents: List<ContentItem>,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier) {
        SectionTitle(
            title = "왓챠 파티",
            modifier = Modifier.padding(horizontal = 16.dp),
            showMore = true,
            titleColor = LetsTheme.colors.textPrimary,
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ){
            items(contents) { content ->
                PartyItem(
                    contentImg = content.image,
                    startTime = content.startTime,
                    tag = content.tag
                )
            }
        }
    }
}

@Composable
private fun PartyItem(
    @DrawableRes contentImg: Int,
    startTime: String?,
    tag: String?,
){
    Column(
        modifier = Modifier
            .width(200.dp)
            .background(color = LetsTheme.colors.surface)
    ) {
        PartyImageItem(contentImg = contentImg)

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = startTime.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            color = LetsTheme.colors.primaryRed,
            style = LetsTheme.typography.subtitle.body_12,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = tag.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            color = LetsTheme.colors.textPrimary,
            style = LetsTheme.typography.subtitle.body_12,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun PartyImageItem(
    @DrawableRes contentImg: Int,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
    ){
        Image(
            painter = painterResource(id = contentImg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        Box(
            modifier = Modifier
                .padding(5.dp)
                .size(35.dp)
                .background(color = LetsTheme.colors.textPrimary, shape = CircleShape)
                .align(Alignment.TopEnd),
            contentAlignment = Alignment.Center
        ){
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_alarm_18),
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewNewContent(){
    LetsTheme {
        PartySection(
            contents = listOf(
                ContentItem(id = 1, title = "테스트1", image = R.drawable.img_content_7),
                ContentItem(id = 2, title = "테스트2", image = R.drawable.img_content_8),
                ContentItem(id = 3, title = "테스트3", image = R.drawable.img_content_9),
                ContentItem(id = 3, title = "테스트3", image = R.drawable.img_content_9),
            )
        )
//        PartyItem(
//            contentImg = R.drawable.img_content_1,
//            startTime = "12시 19분에 시작합니다",
//            tag = "#메롱"
//        )
    }
}
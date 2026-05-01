package com.example.letssopt.presentation.home.component

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.LetsTheme
import com.example.letssopt.domain.model.ContentModel

@Composable
fun UpcomingSection(
    contents: List<ContentModel>,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier) {
        SectionTitle(
            title = "공개 예정 콘텐츠",
            modifier = Modifier.padding(horizontal = 16.dp),
            showMore = true,
            titleColor = LetsTheme.colors.textPrimary,
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 8.dp),
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
        UpcomingSection(
            contents = listOf(
                ContentModel(id = 1, title = "테스트1", image = R.drawable.img_content_7),
                ContentModel(id = 2, title = "테스트2", image = R.drawable.img_content_8),
                ContentModel(id = 3, title = "테스트3", image = R.drawable.img_content_9),
                ContentModel(id = 3, title = "테스트3", image = R.drawable.img_content_9),
            )
        )
    }
}
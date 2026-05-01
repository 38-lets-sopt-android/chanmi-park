package com.example.letssopt.presentation.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.LetsTheme
import com.example.letssopt.domain.model.ContentModel
import kotlin.math.absoluteValue

@Composable
fun NewContentSection(
    contents: List<ContentModel>,
    pagerState: PagerState,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier) {
        SectionTitle(
            title = "방금 막 도착한 신상 컨텐츠",
            subtitle = "예능부터 드라마까지!",
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 40.dp),
            pageSpacing = 16.dp,
            modifier = Modifier.fillMaxWidth(),
        ) { page ->
            val content = contents[page % contents.size]
            val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)

            SectionItem(
                contentImg = content.image,
                contentTitle = content.title,
                width = 280.dp,
                height = 160.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        alpha = lerp(
                            start = 0.2f,
                            stop = 1f,
                            fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f),
                        )
                    }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewNewContent(){
    val previewContents = listOf(
        ContentModel(id = 1, title = "테스트1", image = R.drawable.img_content_7),
        ContentModel(id = 2, title = "테스트2", image = R.drawable.img_content_8),
        ContentModel(id = 3, title = "테스트3", image = R.drawable.img_content_9),
    )
    val pagerState = rememberPagerState(pageCount = { previewContents.size })

    LetsTheme {
        NewContentSection(
            contents = previewContents,
            pagerState = pagerState,
        )
    }
}
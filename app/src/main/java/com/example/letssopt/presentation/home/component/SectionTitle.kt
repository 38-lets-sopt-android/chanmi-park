package com.example.letssopt.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.theme.LetsTheme

@Composable
fun SectionTitle(
    title: String,
    modifier : Modifier = Modifier,
    subtitle: String? = null,
    showMore: Boolean = false,
    titleColor: Color = LetsTheme.colors.textPrimary,
){
    Row (
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Column() {
            Text(
                text = title,
                color = titleColor,
                style = LetsTheme.typography.title.bold_20,
            )

            if (subtitle != null){
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = subtitle,
                    color = LetsTheme.colors.textSecondary,
                    style = LetsTheme.typography.subtitle.body_16,
                )
            }
        }

        if (showMore){
            Text(
                text = "더보기",
                color = LetsTheme.colors.textSecondary,
                style = LetsTheme.typography.subtitle.body_12,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSectionTitle(){
    LetsTheme {
        SectionTitle(
            title = "예능부터 드라마까지!",
        )
    }
}

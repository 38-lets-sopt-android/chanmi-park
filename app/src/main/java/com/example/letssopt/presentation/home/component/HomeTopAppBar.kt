package com.example.letssopt.presentation.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.LetsTheme

@Composable
fun HomeTopAppBar(
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 23.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 14.dp,
            alignment = Alignment.End,
        )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_top_watch_24),
            contentDescription = null,
            modifier = modifier.size(24.dp),
            tint = LetsTheme.colors.textPrimary
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_top_noti_24),
            contentDescription = null,
            modifier = modifier.size(24.dp),
            tint = LetsTheme.colors.textPrimary
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_top_profile_24),
            contentDescription = null,
            modifier = modifier
                .size(24.dp)
                .clickable { onProfileClick() },
            tint = LetsTheme.colors.textPrimary
        )
    }
}

@Preview
@Composable
private fun PreviewNewContent(){
    LetsTheme {
        HomeTopAppBar(onProfileClick = {})
    }
}
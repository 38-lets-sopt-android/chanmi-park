package com.example.letssopt.presentation.profile.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.theme.LetsTheme

@Composable
fun ProfileSection(
    title: String,
    description : String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            color = LetsTheme.colors.textPrimary,
            style = LetsTheme.typography.subtitle.body_16
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = description,
            color = LetsTheme.colors.textSecondary,
            style = LetsTheme.typography.subtitle.body_12
        )
    }
}

@Preview
@Composable
private fun ProfileSectionPreview() {
    LetsTheme {
        ProfileSection(
            title = "아이디",
            description = "chanmi123"
        )
    }
}


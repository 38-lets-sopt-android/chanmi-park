package com.example.letssopt.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.theme.LetsTheme

@Composable
fun LetsButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
){
    Column (
        modifier = modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = if(enabled) LetsTheme.colors.primaryRed else LetsTheme.colors.surface)
            .clickable(enabled = enabled, onClick = onClick)
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        Text(
            text = text,
            color = if(enabled) LetsTheme.colors.textPrimary else LetsTheme.colors.placeholder,
            style = LetsTheme.typography.subtitle.body_16
        )
    }
}

@Preview
@Composable
private fun LetsButtonPreview(){
    LetsTheme {
        LetsButton(
            text = "로그인",
            onClick = {},
            modifier = Modifier,
            enabled = false,
        )
    }
}
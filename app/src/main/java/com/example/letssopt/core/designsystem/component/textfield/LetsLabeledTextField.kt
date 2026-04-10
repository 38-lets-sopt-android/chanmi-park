package com.example.letssopt.core.designsystem.component.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.theme.LetsTheme

@Composable
fun LetsLabeledTextField(
    text: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
){
    Column (
        modifier = modifier.fillMaxWidth()
    ){
        Text(
            text = text,
            style = LetsTheme.typography.subtitle.caption_13,
            color = LetsTheme.colors.textSecondary,
        )

        Spacer(modifier = Modifier.height(4.dp))

        LetsTextField(
            placeholder = placeholder,
            value = value,
            onValueChange = onValueChange,
            isPassword = isPassword,
        )
    }
}

@Preview
@Composable
private fun LetsTextFieldPreview(){
    LetsTheme {
        var text by remember { mutableStateOf("") }

        LetsLabeledTextField(
            text = "로그인",
            placeholder = "이메일 주소를 입력하세요",
            value = text,
            onValueChange = { text = it },
        )
    }
}
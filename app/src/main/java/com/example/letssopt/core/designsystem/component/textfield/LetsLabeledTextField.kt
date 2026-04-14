package com.example.letssopt.core.designsystem.component.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
){
    Column (
        modifier = modifier.fillMaxWidth()
    ){
        Text(
            text = label,
            style = LetsTheme.typography.subtitle.caption_13,
            color = LetsTheme.colors.textSecondary,
        )

        Spacer(modifier = Modifier.height(4.dp))

        LetsTextField(
            modifier = Modifier,
            placeholder = placeholder,
            value = value,
            onValueChange = onValueChange,
            isPassword = isPassword,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
        )
    }
}

@Preview
@Composable
private fun LetsTextFieldPreview(){
    LetsTheme {
        var text by remember { mutableStateOf("") }

        LetsLabeledTextField(
            label = "로그인",
            placeholder = "이메일 주소를 입력하세요",
            value = text,
            onValueChange = { text = it },
        )
    }
}
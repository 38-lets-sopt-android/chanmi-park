package com.example.letssopt.core.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.theme.LetsTheme

@Composable
fun LetsTextField(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
){
    val visualTransformation =
        if (isPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    Column (
        modifier = modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = LetsTheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        BasicTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 18.dp),
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(color = LetsTheme.colors.textPrimary),
            singleLine = true,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            decorationBox ={ innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = LetsTheme.typography.subtitle.caption_13,
                        color = LetsTheme.colors.placeholder,
                    )
                }
                innerTextField()
            }
        )
    }
}

@Preview
@Composable
private fun LetsTextFieldPreview(){
    LetsTheme {
        var text by remember { mutableStateOf("") }

        LetsTextField(
            placeholder = "이메일 주소를 입력하세요",
            value = text,
            onValueChange = { text = it },
        )
    }
}
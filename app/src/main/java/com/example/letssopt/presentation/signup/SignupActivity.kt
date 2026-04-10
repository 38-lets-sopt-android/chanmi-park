package com.example.letssopt.presentation.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.component.button.LetsButton
import com.example.letssopt.core.designsystem.component.textfield.LetsLabeledTextField
import com.example.letssopt.core.designsystem.theme.LetsTheme

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LetsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignupScreen(
                        modifier = Modifier.padding(innerPadding),
                        onSignupComplete = { userId, userPw ->
                            val resultIntent = Intent().apply {
                                putExtra("userId", userId)
                                putExtra("userPw", userPw)
                            }
                            setResult(RESULT_OK, resultIntent)
                            finish()
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    onSignupComplete: (String, String) -> Unit
){
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column (
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .background(color = LetsTheme.colors.background)
            .padding(horizontal = 20.dp)
            .padding(top = 60.dp, bottom = 26.dp)
    ){
        Text(
            text = "watcha",
            modifier = Modifier.fillMaxWidth(),
            color = LetsTheme.colors.primaryRed,
            style = LetsTheme.typography.title.bold_24,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = "회원가입",
            modifier = Modifier.fillMaxWidth(),
            color = LetsTheme.colors.textPrimary,
            style = LetsTheme.typography.title.bold_20,
        )

        Spacer(modifier = Modifier.height(36.dp))

        LetsLabeledTextField(
            text = "이메일",
            placeholder = "이메일 주소를 입력하세요",
            value = emailText,
            onValueChange = { emailText = it },
        )

        Spacer(modifier = Modifier.height(18.dp))

        LetsLabeledTextField(
            text = "비밀번호",
            placeholder = "비밀번호를 입력하세요",
            value = passwordText,
            onValueChange = { passwordText = it },
            isPassword = true,
        )

        Spacer(modifier = Modifier.height(18.dp))

        LetsLabeledTextField(
            text = "비밀번호 확인",
            placeholder = "비밀번호를 다시 입력하세요",
            value = passwordText,
            onValueChange = { passwordText = it },
            isPassword = true,
        )

        Spacer(modifier = Modifier.height(280.dp))

        LetsButton(
            text = "회원가입",
            onClick = {
                onSignupComplete(emailText, passwordText)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
        )
    }
}

@Preview
@Composable
private fun SignupPreview(){
    LetsTheme {
        SignupScreen(
            onSignupComplete = { _, _ -> }
        )
    }
}
package com.example.letssopt.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
    var rePasswordText by remember { mutableStateOf("") }

    val isSignupEnabled = emailText.isNotEmpty() && passwordText.isNotEmpty() && rePasswordText.isNotEmpty()
    val context = LocalContext.current
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
            style = LetsTheme.typography.title.logo_36,
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
            label = "이메일",
            placeholder = "이메일 주소를 입력하세요",
            value = emailText,
            onValueChange = { emailText = it },
        )

        Spacer(modifier = Modifier.height(18.dp))

        LetsLabeledTextField(
            label = "비밀번호",
            placeholder = "비밀번호를 입력하세요",
            value = passwordText,
            onValueChange = { passwordText = it },
            isPassword = true,
        )

        Spacer(modifier = Modifier.height(18.dp))

        LetsLabeledTextField(
            label = "비밀번호 확인",
            placeholder = "비밀번호를 다시 입력하세요",
            value = rePasswordText,
            onValueChange = { rePasswordText = it },
            isPassword = true,
        )

        Spacer(modifier = Modifier.height(280.dp))

        LetsButton(
            text = "회원가입",
            onClick = {
                when{
                    !Patterns.EMAIL_ADDRESS.matcher(emailText).matches() -> {
                        Toast.makeText(context, "올바른 이메일 형식이 아닙니다", Toast.LENGTH_SHORT).show()
                    }
                    passwordText.length < 8 || passwordText.length > 12 -> {
                        Toast.makeText(context, "비밀번호는 8자 이상, 12자 이하여야 합니다", Toast.LENGTH_SHORT).show()
                    }
                    passwordText != rePasswordText -> {
                        Toast.makeText(context, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        onSignupComplete(emailText, passwordText)
                        Toast.makeText(context, "회원가입이되었습니다", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isSignupEnabled,
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
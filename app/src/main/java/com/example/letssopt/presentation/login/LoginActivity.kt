package com.example.letssopt.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.component.button.LetsButton
import com.example.letssopt.core.designsystem.component.textfield.LetsLabeledTextField
import com.example.letssopt.core.designsystem.theme.LetsTheme
import com.example.letssopt.presentation.signup.SignupActivity

class LoginActivity : ComponentActivity() {
    // 📌 역할 1: 결과 받을 준비
    private val signUpLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val id = result.data?.getStringExtra("userId")
            val pw = result.data?.getStringExtra("userPw")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LetsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding),
                        onSignUpClick = {
                            val intent = Intent(this, SignupActivity::class.java)
                            signUpLauncher.launch(intent)
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit
){
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column (
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .background(color = LetsTheme.colors.background)
            .padding(horizontal = 20.dp)
            .padding(top = 60.dp, bottom = 26.dp),
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
            text = "이메일로 로그인",
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

        Spacer(modifier = Modifier.height(334.dp))

        LoginToSignup(
            onClick = {onSignUpClick()}
        )

        Spacer(modifier = Modifier.height(20.dp))

        LetsButton(
            text = "로그인",
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
        )
    }
}

@Composable
private fun LoginToSignup(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = "아직 계정이 없으신가요?",
            color = LetsTheme.colors.textSecondary,
            style = LetsTheme.typography.subtitle.caption_13,
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = "회원가입",
            modifier = Modifier.clickable{
                onClick()
            },
            color = LetsTheme.colors.textSecondary,
            style = LetsTheme.typography.subtitle.caption_13,
            textDecoration = TextDecoration.Underline,
        )
    }
}

@Preview
@Composable
private fun LoginPreview(){
    LetsTheme {
        LoginScreen(
            onSignUpClick = {}
        )
    }
}
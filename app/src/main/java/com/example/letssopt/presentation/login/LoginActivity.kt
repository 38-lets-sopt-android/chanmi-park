package com.example.letssopt.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.Lifecycle
import com.example.letssopt.core.designsystem.component.button.LetsButton
import com.example.letssopt.presentation.login.uistate.LoginUiState
import com.example.letssopt.core.designsystem.component.textfield.LetsLabeledTextField
import com.example.letssopt.core.designsystem.theme.LetsTheme
import com.example.letssopt.presentation.main.MainActivity
import com.example.letssopt.presentation.signup.SignupActivity
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    private val signUpLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val userId = result.data?.getStringExtra("userId") ?: return@registerForActivityResult
            val userPw = result.data?.getStringExtra("userPw") ?: return@registerForActivityResult
            viewModel.saveSignUpInfo(userId, userPw)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    when (event) {
                        is LoginEvent.NavigateToMain -> {
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }
                        is LoginEvent.ShowToast -> {
                            Toast.makeText(this@LoginActivity, event.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        setContent {
            LetsTheme {
                val uiState by viewModel.uiState.collectAsState()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        uiState = uiState,
                        onEmailChange = viewModel::onEmailChange,
                        onPasswordChange = viewModel::onPasswordChange,
                        onSignUpClick = {
                            signUpLauncher.launch(Intent(this, SignupActivity::class.java))
                        },
                        onLoginClick = viewModel::login,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val passwordFocusRequester = remember { FocusRequester() }
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = LetsTheme.colors.background)
            .padding(horizontal = 20.dp)
            .padding(top = 60.dp, bottom = 26.dp),
    ) {
        Text(
            text = "watcha",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = LetsTheme.colors.primaryRed,
            style = LetsTheme.typography.title.logo_36
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = "이메일로 로그인",
            color = LetsTheme.colors.textPrimary,
            style = LetsTheme.typography.title.bold_20,
        )

        Spacer(modifier = Modifier.height(36.dp))

        LetsLabeledTextField(
            label = "이메일",
            placeholder = "이메일 주소를 입력하세요",
            value = uiState.email,
            onValueChange = onEmailChange,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { passwordFocusRequester.requestFocus() }
            ),
        )

        Spacer(modifier = Modifier.height(18.dp))

        LetsLabeledTextField(
            modifier = Modifier.focusRequester(passwordFocusRequester),
            label = "비밀번호",
            placeholder = "비밀번호를 입력하세요",
            value = uiState.password,
            onValueChange = onPasswordChange,
            isPassword = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { onLoginClick() }
            ),
        )

        Spacer(modifier = Modifier.weight(1f))

        LoginToSignup(onClick = onSignUpClick)

        Spacer(modifier = Modifier.height(20.dp))

        LetsButton(
            text = "로그인",
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState.isLoginEnabled && !uiState.isLoading,
        )
    }
}

@Composable
private fun LoginToSignup(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "아직 계정이 없으신가요?",
            color = LetsTheme.colors.textSecondary,
            style = LetsTheme.typography.subtitle.caption_13,
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = "회원가입",
            modifier = Modifier.clickable { onClick() },
            color = LetsTheme.colors.textSecondary,
            style = LetsTheme.typography.subtitle.caption_13,
            textDecoration = TextDecoration.Underline,
        )
    }
}

@Preview
@Composable
private fun LoginPreview() {
    LetsTheme {
        LoginScreen(
            uiState = LoginUiState(),
            onEmailChange = {},
            onPasswordChange = {},
            onSignUpClick = {},
            onLoginClick = {},
        )
    }
}

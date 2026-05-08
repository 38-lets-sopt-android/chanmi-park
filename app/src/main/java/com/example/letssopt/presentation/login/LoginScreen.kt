package com.example.letssopt.presentation.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.designsystem.component.button.LetsButton
import com.example.letssopt.core.designsystem.component.textfield.LetsLabeledTextField
import com.example.letssopt.core.designsystem.theme.LetsTheme
import com.example.letssopt.presentation.login.uistate.LoginUiState
import kotlinx.serialization.Serializable

@Serializable
data object Login

@Composable
fun LoginRoute(
    paddingValues: PaddingValues,
    navigateToHome: () -> Unit,
    navigateToSignup: () -> Unit,
    viewModel: LoginViewModel = viewModel(),
){
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is LoginEvent.NavigateToHome -> navigateToHome()
                is LoginEvent.ShowToast -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    LoginScreen(
        uiState = uiState,
        onIdChange = viewModel::onIdChange,
        onPasswordChange = viewModel::onPasswordChange,
        navigateToSignup = navigateToSignup,
        onLoginClick = viewModel::login,
        modifier = Modifier.padding(paddingValues),
    )
}

@Composable
private fun LoginScreen(
    uiState: LoginUiState,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    navigateToSignup: () -> Unit,
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
            text = "아이디로 로그인",
            color = LetsTheme.colors.textPrimary,
            style = LetsTheme.typography.title.bold_20,
        )

        Spacer(modifier = Modifier.height(36.dp))

        LetsLabeledTextField(
            label = "아이디",
            placeholder = "아이디를 입력하세요",
            value = uiState.id,
            onValueChange = onIdChange,
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

        LoginToSignup(onClick = navigateToSignup)

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
            onIdChange = {},
            onPasswordChange = {},
            navigateToSignup = {},
            onLoginClick = {},
        )
    }
}
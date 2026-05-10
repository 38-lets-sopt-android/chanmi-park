package com.example.letssopt.presentation.signup

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.designsystem.component.button.LetsButton
import com.example.letssopt.core.designsystem.component.textfield.LetsLabeledTextField
import com.example.letssopt.core.designsystem.theme.LetsTheme
import kotlinx.serialization.Serializable

@Serializable
data object Signup

@Composable
fun SignupRoute(
    paddingValues: PaddingValues,
    navigateBack: () -> Unit,
    viewModel: SignUpViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(uiState) {
        when (uiState) {
            is SignUpUiState.Success -> {
                Toast.makeText(context, "회원가입이 되었습니다", Toast.LENGTH_SHORT).show()
                viewModel.resetState()
                navigateBack()
            }
            is SignUpUiState.Error -> {
                Toast.makeText(context, (uiState as SignUpUiState.Error).message, Toast.LENGTH_SHORT).show()
                viewModel.resetState()
            }
            else -> Unit
        }
    }

    SignupScreen(
        modifier = Modifier.padding(paddingValues),
        isLoading = uiState is SignUpUiState.Loading,
        onSignupComplete = { id, pw, name, email, age, part ->
            viewModel.signUp(id, pw, name, email, age, part)
        }
    )
}

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onSignupComplete: (id: String, pw: String, name: String, email: String, age: Int, part: String) -> Unit,
) {
    var idText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var rePasswordText by remember { mutableStateOf("") }
    var nameText by remember { mutableStateOf("") }
    var emailText by remember { mutableStateOf("") }
    var ageText by remember { mutableStateOf("") }
    var partText by remember { mutableStateOf("") }

    val isSignupEnabled = listOf(idText, passwordText, rePasswordText, nameText, emailText, ageText, partText)
        .all { it.isNotEmpty() }
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column (
        modifier = modifier
            .fillMaxSize()
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
            label = "아이디",
            placeholder = "아이디를 입력하세요",
            value = idText,
            onValueChange = { idText = it },
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

        Spacer(modifier = Modifier.height(18.dp))

        LetsLabeledTextField(
            label = "이름",
            placeholder = "이름을 입력하세요",
            value = nameText,
            onValueChange = { nameText = it },
        )

        Spacer(modifier = Modifier.height(18.dp))

        LetsLabeledTextField(
            label = "이메일",
            placeholder = "이메일을 입력하세요",
            value = emailText,
            onValueChange = { emailText = it },
        )

        Spacer(modifier = Modifier.height(18.dp))

        LetsLabeledTextField(
            label = "나이",
            placeholder = "나이를 입력하세요",
            value = ageText,
            onValueChange = { ageText = it },
        )

        Spacer(modifier = Modifier.height(18.dp))

        LetsLabeledTextField(
            label = "파트",
            placeholder = "파트를 입력하세요",
            value = partText,
            onValueChange = { partText = it },
        )

        Spacer(modifier = Modifier.weight(1f))

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
                        onSignupComplete(idText, passwordText, nameText, emailText, ageText.toIntOrNull() ?: 0, partText)
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
            onSignupComplete = { _, _, _, _, _, _ -> }
        )
    }
}
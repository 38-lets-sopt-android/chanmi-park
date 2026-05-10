package com.example.letssopt.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.designsystem.component.button.LetsButton
import com.example.letssopt.core.designsystem.theme.LetsTheme
import com.example.letssopt.presentation.profile.component.ProfileSection
import kotlinx.serialization.Serializable

@Serializable
data object Profile {}

@Composable
fun ProfileRoute(
    paddingValues: PaddingValues,
    viewModel: ProfileViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    ProfileScreen(
        modifier = Modifier.padding(paddingValues),
        loginIdText = uiState.loginId,
        nameText = uiState.name,
        emailText = uiState.email,
        ageText = uiState.age,
        partText = uiState.part,
    )
}

@Composable
private fun ProfileScreen(
    loginIdText: String,
    nameText: String,
    emailText: String,
    ageText: Int,
    partText: String,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 23.dp)
            .padding(top = 70.dp)
    ) {
        Text(
            text = "프로필",
            color = LetsTheme.colors.textPrimary,
            style = LetsTheme.typography.title.bold_20
        )

        Spacer(modifier = Modifier.height(68.dp))

        ProfileSection(
            title = "아이디",
            description = loginIdText
        )

        Spacer(modifier = Modifier.height(30.dp))

        ProfileSection(
            title = "이름",
            description = nameText
        )

        Spacer(modifier = Modifier.height(30.dp))

        ProfileSection(
            title = "이메일",
            description = emailText
        )

        Spacer(modifier = Modifier.height(30.dp))

        ProfileSection(
            title = "나이",
            description = ageText.toString()
        )

        Spacer(modifier = Modifier.height(30.dp))

        ProfileSection(
            title = "파트",
            description = partText
        )

        Spacer(modifier = Modifier.height(30.dp))

        LetsButton(
            text = "다른 유저들 보러가기",
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF141414)
@Composable
private fun ProfileScreenPreview() {
    LetsTheme {
        ProfileScreen(
            loginIdText = "lesly",
            nameText = "박찬미",
            emailText = "chanmi@example.com",
            ageText = 24,
            partText = "Android",
        )
    }
}
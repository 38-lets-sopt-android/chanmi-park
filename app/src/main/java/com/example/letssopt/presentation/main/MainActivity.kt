package com.example.letssopt.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.letssopt.core.designsystem.theme.LetsTheme
import com.example.letssopt.presentation.main.component.MainBottomBar
import com.example.letssopt.presentation.main.component.MainTab
import com.example.letssopt.presentation.main.navigation.MainNavHost
import com.example.letssopt.presentation.main.navigation.rememberMainAppState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LetsTheme {
                val appState = rememberMainAppState()
                val isBottomBarVisible by appState.isBottomBarVisible.collectAsState()
                val currentTab by appState.currentTab.collectAsState()

                Scaffold(
                    bottomBar = {
                        if (isBottomBarVisible) {
                            MainBottomBar(
                                tabs = MainTab.entries.toList(),
                                currentTab = currentTab ?: MainTab.HOME,
                                onTabSelected = { appState.navigateToTab(it) }
                            )
                        }
                    }
                ) { innerPadding ->
                    MainNavHost(
                        appState = appState,
                        paddingValues = innerPadding,
                    )
                }
            }
        }
    }
}

package com.example.letssopt.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.letssopt.core.designsystem.theme.LetsTheme
import com.example.letssopt.presentation.collection.CollectionRoute
import com.example.letssopt.presentation.home.HomeRoute
import com.example.letssopt.presentation.main.component.MainBottomBar
import com.example.letssopt.presentation.main.component.MainTab
import com.example.letssopt.presentation.purchase.PurchaseRoute
import com.example.letssopt.presentation.search.SearchRoute
import com.example.letssopt.presentation.webtoon.WebtoonRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LetsTheme {
                var currentTab by remember { mutableStateOf(MainTab.HOME) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        MainBottomBar(
                            tabs = MainTab.entries.toList(),
                            currentTab = currentTab,
                            onTabSelected = { currentTab = it },
                            modifier = Modifier.navigationBarsPadding()
                        )
                    }
                ) { innerPadding ->
                    when(currentTab){
                        MainTab.HOME -> HomeRoute(modifier = Modifier.padding(innerPadding))
                        MainTab.SEARCH -> SearchRoute()
                        MainTab.PURCHASE -> PurchaseRoute()
                        MainTab.WEBTOON -> WebtoonRoute()
                        MainTab.COLLECTION -> CollectionRoute()
                    }
                }
            }
        }
    }
}
package com.example.letssopt.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.letssopt.presentation.collection.Collection
import com.example.letssopt.presentation.home.Home
import com.example.letssopt.presentation.main.component.MainTab
import com.example.letssopt.presentation.purchase.Purchase
import com.example.letssopt.presentation.search.Search
import com.example.letssopt.presentation.webtoon.Webtoon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainAppState(
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope,
) {
    private val currentDestination: StateFlow<NavDestination?> =
        navController.currentBackStackEntryFlow
            .map { it.destination }
            .stateIn(
                scope = coroutineScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = null
            )

    val isBottomBarVisible: StateFlow<Boolean> =
        currentDestination
            .map { dest ->
                dest?.hierarchy?.any {
                    it.hasRoute(Home::class) ||
                            it.hasRoute(Search::class) ||
                            it.hasRoute(Purchase::class) ||
                            it.hasRoute(Webtoon::class) ||
                            it.hasRoute(Collection::class)
                } ?: false
            }
            .stateIn(
                scope = coroutineScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = false
            )

    val currentTab: StateFlow<MainTab?> =
        currentDestination
            .map { dest ->
                when {
                    dest?.hierarchy?.any { it.hasRoute(Home::class) } == true -> MainTab.HOME
                    dest?.hierarchy?.any { it.hasRoute(Search::class) } == true -> MainTab.SEARCH
                    dest?.hierarchy?.any { it.hasRoute(Purchase::class) } == true -> MainTab.PURCHASE
                    dest?.hierarchy?.any { it.hasRoute(Webtoon::class) } == true -> MainTab.WEBTOON
                    dest?.hierarchy?.any { it.hasRoute(Collection::class) } == true -> MainTab.COLLECTION
                    else -> null
                }
            }
            .stateIn(
                scope = coroutineScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = null
            )

    fun navigateToTab(tab: MainTab) {
        val route = when (tab) {
            MainTab.HOME -> Home
            MainTab.SEARCH -> Search
            MainTab.PURCHASE -> Purchase
            MainTab.WEBTOON -> Webtoon
            MainTab.COLLECTION -> Collection
        }
        navController.navigate(route) {
            popUpTo<Home> { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun rememberMainAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): MainAppState {
    return remember(navController, coroutineScope) {
        MainAppState(
            navController = navController,
            coroutineScope = coroutineScope,
        )
    }
}
package com.example.letssopt.presentation.main.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.letssopt.presentation.collection.Collection
import com.example.letssopt.presentation.collection.CollectionRoute
import com.example.letssopt.presentation.home.Home
import com.example.letssopt.presentation.home.HomeRoute
import com.example.letssopt.presentation.login.Login
import com.example.letssopt.presentation.login.LoginRoute
import com.example.letssopt.presentation.profile.Profile
import com.example.letssopt.presentation.profile.ProfileRoute
import com.example.letssopt.presentation.purchase.Purchase
import com.example.letssopt.presentation.purchase.PurchaseRoute
import com.example.letssopt.presentation.search.Search
import com.example.letssopt.presentation.search.SearchRoute
import com.example.letssopt.presentation.signup.Signup
import com.example.letssopt.presentation.signup.SignupRoute
import com.example.letssopt.presentation.webtoon.Webtoon
import com.example.letssopt.presentation.webtoon.WebtoonRoute

@Composable
fun MainNavHost(
    appState: MainAppState,
    paddingValues: PaddingValues,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = Login,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(durationMillis = 300)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(durationMillis = 300)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(durationMillis = 300)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(durationMillis = 300)
            )
        },
    ){
        composable<Login> {
            LoginRoute(
                paddingValues = paddingValues,
                navigateToHome = {
                    navController.navigate(Home) {
                        popUpTo<Login> {inclusive = true}
                    }
                },
                navigateToSignup = {navController.navigate(Signup)}
            )
        }

        composable<Signup> {
            SignupRoute(
                paddingValues = paddingValues,
                navigateBack = { navController.popBackStack() },
            )
        }

        composable<Home> {
            HomeRoute(
                navigateToProfile = { navController.navigate(Profile) },
                modifier = Modifier,
            )
        }

        composable<Search> {
            SearchRoute(
                paddingValues = paddingValues,
            )
        }

        composable<Purchase> {
            PurchaseRoute(
                paddingValues = paddingValues,
            )
        }

        composable<Webtoon> {
            WebtoonRoute(
                paddingValues = paddingValues,
            )
        }

        composable<Collection> {
            CollectionRoute(
                paddingValues = paddingValues,
            )
        }

        composable<Profile> {
            ProfileRoute(
                paddingValues = paddingValues,
            )
        }
    }
}
package com.example.letssopt.core.designsystem.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


object LetsTheme {
    val colors: LetsColors
        @Composable
        @ReadOnlyComposable
        get() = LocalLetsColorsProvider.current

    val typography: LetsTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalLetsTypographyProvider.current
}

@Composable
private fun ProvideLetsColorsAndTypography(
    colors: LetsColors,
    typography: LetsTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalLetsColorsProvider provides colors,
        LocalLetsTypographyProvider provides typography,
        content = content
    )
}

@Composable
fun LetsTheme(
    content: @Composable () -> Unit
) {
    ProvideLetsColorsAndTypography(
        colors = defaultLetsColors,
        typography = defaultLestsTypography
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            // optional
            SideEffect {
                (view.context as Activity).window.run {
                    WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = true
                }
            }
        }

        MaterialTheme(
            content = content
        )
    }
}
package com.example.letssopt.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val PrimaryRed = Color(0xFFE8003C)
private val Background = Color(0xFF141414)
private val Surface = Color(0xFF2A2A2A)
private val TextPrimary = Color(0xFFFFFFFF)
private val TextSecondary = Color(0xFF999999)
private val Placeholder = Color(0xFF666666)
private val Disabled = Color(0xFF333333)
private val Black = Color(0xFF000000)

@Immutable
data class LetsColors(
    val primaryRed: Color = PrimaryRed,
    val background: Color = Background,
    val surface: Color = Surface,
    val textPrimary: Color = TextPrimary,
    val textSecondary: Color = TextSecondary,
    val placeholder: Color = Placeholder,
    val disabled: Color = Disabled,
    val black: Color = Black,
)

val defaultLetsColors = LetsColors(
    primaryRed = PrimaryRed,
    background = Background,
    surface = Surface,
    textPrimary = TextPrimary,
    textSecondary = TextSecondary,
    placeholder = Placeholder,
    disabled = Disabled,
    black = Black,
)

val LocalLetsColorsProvider = staticCompositionLocalOf { defaultLetsColors }
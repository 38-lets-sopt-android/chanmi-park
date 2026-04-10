package com.example.letssopt.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.letssopt.R

object InterFont {
    val Bold = FontFamily(Font(R.font.inter_bold))
    val Regular = FontFamily(Font(R.font.inter_regular))
}

sealed interface TypographyTokens {
    @Immutable
    data class Title(
        val bold_24: TextStyle,
        val bold_20: TextStyle
    )

    @Immutable
    data class Subtitle(
        val body_16: TextStyle,
        val caption_13: TextStyle
    )
}

@Immutable
data class LetsTypography(
    val title: TypographyTokens.Title,
    val subtitle: TypographyTokens.Subtitle
)

val defaultLestsTypography = LetsTypography(
    title = TypographyTokens.Title(
        bold_24 = TextStyle(
            fontFamily = InterFont.Bold,
            fontSize = 24.sp,
            lineHeight = 28.8.sp, // 120%
        ),
        bold_20 = TextStyle(
            fontFamily = InterFont.Bold,
            fontSize = 20.sp,
            lineHeight = 28.8.sp, // 120%
        )
    ),
    subtitle = TypographyTokens.Subtitle(
        body_16 = TextStyle(
            fontFamily = InterFont.Regular,
            fontSize = 16.sp,
            lineHeight = 28.8.sp, // 120%
        ),
        caption_13 = TextStyle(
            fontFamily = InterFont.Regular,
            fontSize = 13.sp,
            lineHeight = 28.8.sp, // 120%
        )
    )
)

val LocalLetsTypographyProvider = staticCompositionLocalOf { defaultLestsTypography }
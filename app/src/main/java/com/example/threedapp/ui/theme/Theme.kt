package com.example.threedapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ThreeDAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val systemUiController = rememberSystemUiController()

    val colors = if (darkTheme) {
        systemUiController.setStatusBarColor(
            color = BackgroundColorDark
        )
        DarkColorPalette
    } else {
        systemUiController.setStatusBarColor(
            color = BackgroundColorLight
        )
        LightColorPalette
    }
    CompositionLocalProvider(LocalColors provides colors) {
        MaterialTheme(
            colors = colors.material,
            content = content,
        )
    }
}

data class MyColors(
    val material: Colors,
    val interviewHeaderColor: Color,
    val answeredColor: Color,
    val correctColor: Color,
    val incorrectColor: Color,
    val invisible: Color,
    val incorrectColorALittle: Color,
    val correctColorALittle: Color,

    ) {
    val primary: Color get() = material.primary
    val primaryVariant: Color get() = material.primaryVariant
    val secondary: Color get() = material.secondary
    val secondaryVariant: Color get() = material.secondaryVariant
    val background: Color get() = material.background
    val surface: Color get() = material.surface
    val error: Color get() = material.error
    val onPrimary: Color get() = material.onPrimary
    val onSecondary: Color get() = material.onSecondary
    val onBackground: Color get() = material.onBackground
    val onSurface: Color get() = material.onSurface
    val onError: Color get() = material.onError
    val isLight: Boolean get() = material.isLight
}

private val DarkColorPalette = MyColors(
    material = darkColors(
        primary = PrimaryDark,
        primaryVariant = Purple700,
        secondary = SecondaryDark,
        background = BackgroundColorDark,
    ),
    interviewHeaderColor = InterviewHeaderColorDark,
    answeredColor = AnsweredColorDark,
    correctColor = CorrectColorDark,
    incorrectColor = IncorrectColorDark,
    invisible = InvisibaleColor,
    incorrectColorALittle = IncorrectColorALittleDark,
    correctColorALittle = CorrectColorDarkALittle,
)

private val LightColorPalette = MyColors(
    material = lightColors(
        primary = PrimaryLight,
        primaryVariant = Purple700,
        secondary = SecondaryLight,
        background = BackgroundColorLight,
    ),
    interviewHeaderColor = InterviewHeaderColor,
    answeredColor = AnsweredColorLight,
    correctColor = CorrectColorLight,
    incorrectColor = IncorrectColorLight,
    invisible = InvisibaleColor,
    incorrectColorALittle = IncorrectColorALittleLight,
    correctColorALittle = CorrectColorLightALittle,
)
val MaterialTheme.myColors: MyColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current

private val LocalColors = staticCompositionLocalOf { LightColorPalette }

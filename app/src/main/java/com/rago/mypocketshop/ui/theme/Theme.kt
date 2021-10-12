package com.rago.mypocketshop.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    /*primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,*/
    primary = PrimaryLight,
    primaryVariant = PrimaryDark,
    secondary = SecondaryLight,
    secondaryVariant = SecondaryLight,
    background = DarkBackground,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary
)

private val LightColorPalette = lightColors(
    /*primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = LightBackground*/
    primary = Primary,
    primaryVariant = PrimaryDark,
    secondary = Secondary,
    secondaryVariant = SecondaryDark,
    background = LightBackground,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MyPocketShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
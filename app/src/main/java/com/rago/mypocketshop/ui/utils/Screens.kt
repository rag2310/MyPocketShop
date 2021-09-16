package com.rago.mypocketshop.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.rago.mypocketshop.ui.values.MenuLabel
import com.rago.mypocketshop.ui.values.SettingsLabel

sealed class Screens(
    val route: String,
    val icon: ImageVector? = null,
    val label: String = "label"
) {
    object Splash : Screens("splash")
    object Menu : Screens("Menu", Icons.Filled.Menu, MenuLabel)
    object Settings : Screens("settings", Icons.Filled.Settings, SettingsLabel)
    object Products : Screens("Products")

    companion object {
        val itemsBottomBar = listOf(
            Menu,
            Settings
        )
    }
}
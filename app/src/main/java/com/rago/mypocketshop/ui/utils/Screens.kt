package com.rago.mypocketshop.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.rago.mypocketshop.ui.values.BottomBarLabels
import com.rago.mypocketshop.ui.values.Routes

sealed class Screens(
    val route: String,
    val icon: ImageVector? = null,
    val label: String = "label"
) {
    object Splash : Screens(Routes.Splash)
    object Menu : Screens(Routes.Menu, Icons.Filled.Menu, BottomBarLabels.MenuLabel)
    object Settings : Screens(Routes.Settings, Icons.Filled.Settings, BottomBarLabels.SettingsLabel)
    object Products : Screens(Routes.Products)
    object Account : Screens(Routes.Account)
    object Params : Screens(Routes.Params)

    companion object {
        val itemsBottomBar = listOf(
            Menu,
            Settings
        )
    }
}
package com.rago.mypocketshop.ui.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rago.mypocketshop.ui.utils.Screens

@Composable
fun BottomBarLayout(navController: NavController, content: @Composable () -> Unit) {
    MainLayout {
        Scaffold(bottomBar = {
            BottomNavigation(elevation = 2.dp) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                Screens.itemsBottomBar.forEach { screen ->
                    // Items Navigation is Here!!!!
                    BottomNavigationItem(
                        icon = { Icon(imageVector = screen.icon!!, contentDescription = null) },
                        label = {
                            Text(text = screen.label)
                        },
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        })
                }
            }
        }) {
            content()
        }
    }
}
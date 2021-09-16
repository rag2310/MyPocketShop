package com.rago.mypocketshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rago.mypocketshop.ui.components.BottomBarLayout
import com.rago.mypocketshop.ui.components.MainLayout
import com.rago.mypocketshop.ui.screen.menu.MenuScreen
import com.rago.mypocketshop.ui.screen.menu.MenuViewModel
import com.rago.mypocketshop.ui.screen.products.ProductsScreen
import com.rago.mypocketshop.ui.screen.products.ProductsViewModel
import com.rago.mypocketshop.ui.screen.settings.SettingsScreen
import com.rago.mypocketshop.ui.screen.splash.SplashScreen
import com.rago.mypocketshop.ui.screen.splash.SplashViewModel
import com.rago.mypocketshop.ui.utils.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screens.Splash.route) {
                composable(route = Screens.Splash.route) {
                    val viewModel = hiltViewModel<SplashViewModel>()
                    MainLayout {
                        SplashScreen(navController = navController, viewModel = viewModel)
                    }
                }

                composable(route = Screens.Menu.route) {
                    val viewModel = hiltViewModel<MenuViewModel>()
                    BottomBarLayout(navController = navController) {
                        MenuScreen(navController = navController, viewModel = viewModel)
                    }
                }

                composable(route = Screens.Settings.route) {
                    BottomBarLayout(navController = navController) {
                        SettingsScreen(navController = navController)
                    }
                }

                composable(route = Screens.Products.route) {
                    val viewModel = hiltViewModel<ProductsViewModel>()
                    MainLayout {
                        ProductsScreen(navController = navController, viewModel = viewModel)
                    }
                }
            }

        }
    }
}


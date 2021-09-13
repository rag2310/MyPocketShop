package com.rago.mypocketshop.ui.screen.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Inventory
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rago.mypocketshop.R
import com.rago.mypocketshop.ui.screen.menu.components.MenuItem
import com.rago.mypocketshop.ui.utils.Currency
import com.rago.mypocketshop.ui.utils.Screens

@Composable
fun MenuScreen(navController: NavController) {
    MenuContent(navController = navController)
}

@Composable
fun MenuContent(navController: NavController) {

    val earnings = 2000.0
    val debt = 200.0
    val padding = 8.dp

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 48.dp)
        ) {
            Column {
                Text(
                    text = "Hola Rodolfo,",
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_bold))
                )
                Text(
                    text = "¿Qué quieres hacer hoy?",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_medium))
                )
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {

            val currencyModifier = Modifier
                .weight(1f)
                .padding(start = padding)

            CurrencyColumn(
                modifier = currencyModifier,
                value = earnings,
                subTitle = "Ganancias del día"
            )

            CurrencyColumn(
                modifier = currencyModifier,
                value = debt,
                subTitle = "Deuda del día"
            )
        }

        val modifier: Modifier = Modifier
            .weight(1f)
            .padding(horizontal = padding)

        MenuRow {
            MenuItem(
                modifier = modifier,
                title = "Ventas",
                subTitle = "Detalles de las ventas efectuadas",
                onClick = {
                    println("Ventas")
                },
//                color = Color.Blue,
                icon = Icons.Outlined.Storefront
            )

            MenuItem(
                modifier = modifier,
                title = "Productos",
                subTitle = "Listas de Productos",
                onClick = {
                    println("Productos")
                    navController.navigate(Screens.Products.route)
                },
//                color = Color.Red,
                icon = Icons.Outlined.Inventory2
            )
        }

        MenuRow {
            MenuItem(
                modifier = modifier,
                title = "Inventario",
                subTitle = "Productos en inventario",
                onClick = {
                    println("Inventario")
                },
//                color = Color.Red,
                icon = Icons.Outlined.Inventory
            )

            MenuItem(
                modifier = modifier,
                title = "Finanzas",
                subTitle = "Detalles de las ventas efectuadas",
                onClick = {
                    println("Finanzas")
                },
//                color = Color.Blue,
                icon = Icons.Outlined.AccountBalanceWallet
            )
        }

    }
}

@Composable
private fun MenuRow(content: @Composable () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(vertical = 8.dp),
    ) {
        content()
    }
}

@Composable
private fun CurrencyColumn(modifier: Modifier, value: Double, subTitle: String) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "$Currency $value", fontFamily = FontFamily(
                Font(R.font.roboto_bold)
            ), fontSize = 26.sp, modifier = Modifier.padding(bottom = 6.dp)
        )
        Text(
            text = subTitle, fontFamily = FontFamily(
                Font(R.font.roboto_light)
            ), fontSize = 16.sp
        )
    }
}
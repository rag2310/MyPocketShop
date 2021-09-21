package com.rago.mypocketshop.ui.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rago.mypocketshop.BuildConfig
import com.rago.mypocketshop.R
import com.rago.mypocketshop.ui.components.MainTitle
import com.rago.mypocketshop.ui.components.SubTitle
import com.rago.mypocketshop.ui.utils.Screens

@Composable
fun SettingsScreen(navController: NavController) {
    SettingsContent(navController = navController)
}

@Composable
fun SettingsContent(navController: NavController) {

    val result =
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<String>("username")
            ?.observeAsState()

    result?.value?.let {
        println(it)
    }

    val context = LocalContext.current
    val intentGithub = remember {
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://github.com/rag2310/")
        )
    }
    val intentLinkedin = remember {
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.linkedin.com/in/rodolfo-gutierrez-776115116")
        )
    }

    val intentEmail = remember {
        Intent(
            Intent.ACTION_SEND
        )
    }
    intentEmail.data = Uri.parse("mailto:")
    intentEmail.type = "text/plain"
    val to = arrayOf("rag.ortega@gmail.com")
    intentEmail.putExtra(Intent.EXTRA_EMAIL, to)
    intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Mi tienda de Bolsillo")
    intentEmail.putExtra(Intent.EXTRA_TEXT, "Hola....")
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MainTitle(
            "Ajustes",
            paddingValues = PaddingValues(start = 20.dp, top = 15.dp, bottom = 15.dp)
        )
        SettingRow(Icons.Filled.AccountCircle, "Cuenta") {
            navController.navigate(Screens.Account.route)
        }
        SettingRow(Icons.Filled.AccountCircle, "Parametros") {
            println("Parametros")
        }
        Text(
            text = "Acerca de ${stringResource(id = R.string.nombre_app)}",
            fontFamily = FontFamily(
                Font(R.font.roboto_black)
            ), fontSize = 20.sp,
            modifier = Modifier.padding(20.dp)
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${stringResource(id = R.string.nombre_app)} - ${BuildConfig.VERSION_NAME}",
                fontFamily = FontFamily(
                    Font(R.font.roboto_light)
                ),
                fontSize = 14.sp
            )
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
        ) {
            IconButton(onClick = {
                context.startActivity(intentGithub)
            }) {
                Icon(
                    painterResource(id = R.drawable.ic_octicons_mark_github),
                    contentDescription = null,
                    Modifier.size(32.dp)
                )
            }
            IconButton(onClick = {
                context.startActivity(Intent.createChooser(intentEmail, "Send email..."))
            }) {
                Icon(
                    Icons.Filled.Email,
                    contentDescription = null,
                    Modifier.size(32.dp)
                )
            }
            IconButton(onClick = {
                context.startActivity(intentLinkedin)
            }) {
                Icon(
                    painterResource(id = R.drawable.ic_logotipo_de_linkedin),
                    contentDescription = null,
                    Modifier.size(32.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingRow(
    icon: ImageVector = Icons.Filled.AccountCircle,
    title: String = "Cuenta",
    onClick: () -> Unit = {}
) {
    Row(
        Modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .padding(start = 20.dp, bottom = 10.dp, top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.padding(end = 15.dp)
        )
        SubTitle(title = title)
    }
    Box(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Gray)
    )
}

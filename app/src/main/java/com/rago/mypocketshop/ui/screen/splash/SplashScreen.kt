package com.rago.mypocketshop.ui.screen.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rago.mypocketshop.BuildConfig
import com.rago.mypocketshop.R
import com.rago.mypocketshop.ui.components.MainTitle
import com.rago.mypocketshop.ui.components.SubTitle
import kotlinx.coroutines.*

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController) {
    ScreenContent()

    CoroutineScope(Dispatchers.Main).launch{
        delay(1000)
        navController.popBackStack()
        navController.navigate("menu")
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenContent() {
    val version = BuildConfig.VERSION_NAME

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MainTitle(
                stringResource(id = R.string.nombre_app),
                paddingValues = PaddingValues(bottom = 8.dp)
            )
            CircularProgressIndicator()
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            SubTitle(title = "Version $version", paddingValues = PaddingValues(bottom = 10.dp))
        }
    }
}
package com.rago.mypocketshop.ui.screen.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.rago.mypocketshop.ui.utils.Screens

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel) {

    viewModel.mainProcess()
    val process by viewModel.process.observeAsState(initial = "")
    val complete by viewModel.complete.observeAsState(initial = false)

    ScreenContent(process = process)

    if (complete) {
        navController.popBackStack()
        navController.navigate(Screens.Menu.route)
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenContent(process: String = stringResource(id = R.string.loading)) {
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
            CircularProgressIndicator(modifier = Modifier.padding(top = 8.dp))
            SubTitle(process, paddingValues = PaddingValues(top = 2.dp))
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            SubTitle(
                title = "${stringResource(id = R.string.version)} $version",
                paddingValues = PaddingValues(bottom = 10.dp)
            )
        }
    }
}
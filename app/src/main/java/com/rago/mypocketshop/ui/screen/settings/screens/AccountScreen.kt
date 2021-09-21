package com.rago.mypocketshop.ui.screen.settings.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.rago.mypocketshop.ui.components.GenericTextField

@Composable
fun AccountScreen(navController: NavController) {
    var username by remember {
        mutableStateOf("")
    }
    var tooltip by remember {
        mutableStateOf(false)
    }
    var isError by remember {
        mutableStateOf(false)
    }
    var error by remember {
        mutableStateOf("")
    }

    Column(
        Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GenericTextField(
            data = username,
            placeHolder = "Usuario",
            onChangeUsername = {
                username = it
            },
            isError = isError,
            errorMsg = error,
            showTooltip = tooltip,
            closeTooltip = {
                isError = false
                error = ""
                tooltip = !tooltip
            },
            showError = {
                tooltip = !tooltip
            })
        Button(onClick = {
            navController.previousBackStackEntry?.savedStateHandle?.set("username", username)
            navController.popBackStack()
        }) {
            Text(text = "Send")
        }
    }
}
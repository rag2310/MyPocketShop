package com.rago.mypocketshop.ui.screen.settings.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rago.mypocketshop.ui.components.CustomTopBar
import com.rago.mypocketshop.ui.components.GenericTextField
import com.rago.mypocketshop.ui.utils.Username
import com.rago.mypocketshop.ui.utils.formValidations
import com.rago.mypocketshop.ui.values.ButtonLabel
import com.rago.mypocketshop.ui.values.PlaceholderForForms
import com.rago.mypocketshop.ui.values.TitlesForTopBar

@Composable
fun AccountScreen(navController: NavController) {

    var username by remember {
        mutableStateOf(Username)
    }
    var showTooltip by remember {
        mutableStateOf(false)
    }
    var isError by remember {
        mutableStateOf(false)
    }
    var errorMsg by remember {
        mutableStateOf("")
    }

    AccountContent(
        username = username,
        onChangeUsername = {
            username = it
        },
        isError = isError,
        errorMsg = errorMsg,
        showTooltip = showTooltip,
        onCloseTooltip = {
            isError = false
            errorMsg = ""
            showTooltip = !showTooltip
        },
        onShowError = {
            showTooltip = !showTooltip
        },
        onClickNav = {
            val (error, msg) = formValidations(username)
            if (error) {
                isError = error
                errorMsg = msg
            } else {
                navController.previousBackStackEntry?.savedStateHandle?.set("username", username)
                navController.popBackStack()
            }
        },
        onBackNav = {
            navController.popBackStack()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AccountContent(
    username: String = "",
    onChangeUsername: (newUsername: String) -> Unit = {},
    isError: Boolean = false,
    errorMsg: String = "",
    showTooltip: Boolean = false,
    onCloseTooltip: () -> Unit = {},
    onShowError: () -> Unit = {},
    onClickNav: () -> Unit = {},
    onBackNav: () -> Unit = {}
) {
    CustomTopBar(title = TitlesForTopBar.AccountTitle, onBackNav = onBackNav) {

        Box(
            Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Column {
                GenericTextField(
                    data = username,
                    placeHolder = PlaceholderForForms.Username,
                    onChangeUsername = onChangeUsername,
                    isError = isError,
                    errorMsg = errorMsg,
                    showTooltip = showTooltip,
                    onCloseTooltip = onCloseTooltip,
                    onShowError = onShowError
                )

            }
            Button(
                onClick = onClickNav,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Icon(
                        Icons.Filled.Save, contentDescription = null,
                        modifier = Modifier.padding(end = 20.dp)
                    )
                    Text(text = ButtonLabel.Save)
                }
            }
        }

    }
}
package com.rago.mypocketshop.ui.screen.settings.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.rago.mypocketshop.ui.components.CustomTopBar
import com.rago.mypocketshop.ui.values.ButtonLabel
import com.rago.mypocketshop.ui.values.TitlesForTopBar

@Composable
fun ParamsScreen(navController: NavController) {
    val currencies = listOf(
        "C$",
        "$"
    )

    var selectedText by remember {
        mutableStateOf("")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    ParamsContent(
        expanded = expanded,
        selectedText = selectedText,
        onSelectedText = { text: String -> selectedText = text },
        onExpanded = {
            expanded = !expanded
        },
        list = currencies,
        onNavBack = {
            navController.popBackStack()
        },
        onNavResult = {
            navController.previousBackStackEntry?.savedStateHandle?.set("param", selectedText)
            navController.popBackStack()
        }
    )
}

@Composable
fun ParamsContent(
    onNavBack: () -> Unit = {},
    list: List<String>,
    selectedText: String,
    onNavResult: () -> Unit,
    onSelectedText: (String) -> Unit,
    expanded: Boolean,
    onExpanded: () -> Unit
) {
    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    val icon = if (expanded) {
        Icons.Filled.ArrowDropUp
    } else {
        Icons.Filled.ArrowDropDown
    }

    CustomTopBar(onBackNav = onNavBack, title = TitlesForTopBar.ParamTitle) {
        Box(Modifier.fillMaxSize()) {

            Column {
                OutlinedTextField(enabled = false,
                    value = selectedText,
                    onValueChange = onSelectedText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { layoutCoordinates ->
                            textFieldSize = layoutCoordinates.size.toSize()

                        },
                    label = { Text(text = "Currency") },
                    trailingIcon = {
                        Icon(icon, contentDescription = null, modifier = Modifier.clickable {
                            onExpanded()
                        })
                    })
                DropdownMenu(
                    modifier = Modifier.width(with(LocalDensity.current) {
                        textFieldSize.width.toDp()
                    }),
                    expanded = expanded,
                    onDismissRequest = {
                        onExpanded()
                    }
                ) {
                    list.forEach {
                        DropdownMenuItem(onClick = {
                            onSelectedText(it)
                            onExpanded()
                        }) {
                            Text(
                                text = it
                            )
                        }
                    }
                }
            }
            Button(
                onClick = onNavResult,
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

package com.rago.mypocketshop.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ScaffoldTopBar(
    title: String,
    navController: NavController,
    content: @Composable () -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
                Text(title, modifier = Modifier.padding(start = 10.dp))
            }
        }
    }) {
        content()
    }
}
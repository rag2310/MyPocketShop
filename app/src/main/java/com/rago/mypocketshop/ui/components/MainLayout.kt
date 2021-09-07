package com.rago.mypocketshop.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.rago.mypocketshop.ui.theme.MyPocketShopTheme

@Composable
fun MainLayout(content: @Composable () -> Unit) {
    MyPocketShopTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}
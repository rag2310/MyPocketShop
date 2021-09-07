package com.rago.mypocketshop.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rago.mypocketshop.R

@Preview(showBackground = true)
@Composable
fun SubTitle(title:String = "Version 1.0",paddingValues: PaddingValues = PaddingValues(0.dp)) {
    Text(
        modifier = Modifier
            .padding(paddingValues = paddingValues),
        text = title,
        fontFamily = FontFamily(Font(R.font.roboto_medium))
    )
}
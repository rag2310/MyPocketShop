package com.rago.mypocketshop.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rago.mypocketshop.R

@Preview(showBackground = true)
@Composable
fun MainTitle(
    title: String = stringResource(id = R.string.nombre_app),
    paddingValues: PaddingValues = PaddingValues(0.dp),
    color: Color = Color.Unspecified
) {
    Text(
        modifier = Modifier.padding(paddingValues = paddingValues),
        text = title,
        fontFamily = FontFamily(Font(R.font.roboto_bold)), fontSize = 16.sp,
        color = color
    )
}
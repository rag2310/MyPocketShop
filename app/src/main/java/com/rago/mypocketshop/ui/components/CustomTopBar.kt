package com.rago.mypocketshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun CustomTopBar(
    title: String = stringResource(id = R.string.products_title),
    onBackNav: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            Modifier
                .padding(bottom = 10.dp, top = 5.dp)
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp))
                    .background(MaterialTheme.colors.secondary)
            ) {
                IconButton(onClick = onBackNav) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onSecondary
                    )
                }
            }
            Text(
                title,
                fontFamily = FontFamily(Font(R.font.roboto_black)),
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
        content()
    }
}
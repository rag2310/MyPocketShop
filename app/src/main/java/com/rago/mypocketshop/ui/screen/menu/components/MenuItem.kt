package com.rago.mypocketshop.ui.screen.menu.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rago.mypocketshop.R
import com.rago.mypocketshop.ui.components.MainTitle

@Preview(showBackground = true)
@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    title: String = "Productos",
    subTitle: String = "Inventario Actual",
    onClick: () -> Unit = {},
    color: Color = Color.Blue,
    icon: ImageVector = Icons.Outlined.Inventory2
) {
    Card(
        modifier = modifier
            .width(140.dp)
            .fillMaxHeight()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(0),
        backgroundColor = color.copy(alpha = 0.3f),
        elevation = 0.dp
    ) {
        Column(Modifier.fillMaxWidth()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, end = 16.dp, bottom = 40.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                        .background(Color.White)
                ) {
                    Icon(
                        icon,
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 16.dp)
            ) {
                Column(Modifier.fillMaxWidth()) {
                    MainTitle(title)
                    Text(
                        text = subTitle,
                        fontFamily = FontFamily(
                            Font(R.font.roboto_light)
                        ),
                        fontSize = 12.sp,
                        color = color.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}
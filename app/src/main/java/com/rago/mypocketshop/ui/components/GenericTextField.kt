package com.rago.mypocketshop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

@Composable
fun GenericTextField(
    data: String,
    placeHolder: String,
    onChangeUsername: (newUsername: String) -> Unit,
    isError: Boolean,
    errorMsg: String,
    showTooltip: Boolean,
    onCloseTooltip: () -> Unit,
    onShowError: () -> Unit
) {
    Row(Modifier.fillMaxWidth()) {
        OutlinedTextField(value = data, onValueChange = onChangeUsername, placeholder = {
            Text(text = placeHolder)
        }, trailingIcon = {
            if (isError) {
                if (showTooltip) {
                    IconButton(onClick = onCloseTooltip) {
                        Icon(Icons.Filled.Close, contentDescription = null)
                    }
                } else {
                    IconButton(onClick = onShowError) {
                        Icon(
                            Icons.Filled.Info,
                            tint = Color.Red.copy(alpha = 0.6f),
                            contentDescription = null
                        )
                    }
                }
            }
        }, modifier = Modifier.fillMaxWidth())
    }
    val popupHeight = 30.dp
    val cornerSize = 5.dp

    if (showTooltip) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(end = 10.dp, top = 3.dp)
        ) {
            Popup(alignment = Alignment.CenterEnd) {
                // Draw a rectangle shape with rounded corners inside the popup
                Box(
                    Modifier
                        .width(IntrinsicSize.Max)
                        .height(popupHeight)
                        .background(Color.White, RoundedCornerShape(cornerSize))
                        .border(1.dp, Color.Gray, RoundedCornerShape(5.dp))
                        .padding(horizontal = 5.dp)
                ) {
                    Text(
                        text = errorMsg,
                        color = Color.Red,
                        modifier = Modifier
                            .align(Alignment.Center),
                    )
                }
            }
        }
    }
}
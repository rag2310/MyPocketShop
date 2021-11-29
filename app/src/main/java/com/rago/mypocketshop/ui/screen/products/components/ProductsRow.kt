package com.rago.mypocketshop.ui.screen.products.components

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Store
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rago.mypocketshop.R
import com.rago.mypocketshop.data.model.Products
import com.rago.mypocketshop.data.model.SelectedState
import com.rago.mypocketshop.ui.screen.products.ComponentState
import com.rago.mypocketshop.ui.utils.Currency
import java.text.SimpleDateFormat
import java.util.*

private val TAG = "ProductsScreen"

@Preview(showBackground = true)
@Composable
fun ProductsRow(
    product: Products = Products(),
    onSelected: (Products) -> Unit = {},
    onNotSelected: (Products) -> Unit = {},
    selectedProduct: Boolean = false
) {
    val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH)
    val formattedDate = dateFormat.format(product.creationDate.time)

    var toState by rememberSaveable {
        mutableStateOf(ComponentState.Released)
    }

    var toSelected by rememberSaveable {
        mutableStateOf(SelectedState.NotSelected)
    }

    val transition: Transition<ComponentState> = updateTransition(targetState = toState, label = "")

    val selectedTransition: Transition<SelectedState> = updateTransition(
        targetState = product.select,
        label = ""
    )

    val color by transition.animateColor(transitionSpec = {
        when {
            ComponentState.Released isTransitioningTo ComponentState.Pressed -> spring(stiffness = 50f)
            else -> tween(durationMillis = 500)
        }
    }, label = "") { state ->
        when (state) {
            ComponentState.Pressed -> Color.Gray.copy(alpha = 0.4f)
            ComponentState.Released -> Color.White
        }

    }

    val selectedColor by selectedTransition.animateColor(transitionSpec = {
        when {
            SelectedState.NotSelected isTransitioningTo SelectedState.OnSelected -> spring(stiffness = 50f)
            else -> tween(durationMillis = 500)
        }
    }, label = "") { state ->
        when (state) {
            SelectedState.OnSelected -> MaterialTheme.colors.secondary
            SelectedState.NotSelected -> MaterialTheme.colors.primary.copy(alpha = 0.5f)
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 5.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        Log.i(TAG,"product.select ${product.select} == SelectedState.OnSelected ${SelectedState.OnSelected} && selectedProduct $selectedProduct")
                        if(product.select == SelectedState.OnSelected && selectedProduct){
                            Log.i(TAG,"notselectd")
                            product.select = SelectedState.NotSelected
                            onNotSelected(product)
                        } else {
                            Log.i(TAG,"selectd")
                            product.select = SelectedState.OnSelected
                            Log.i(TAG,"selectd ${product.select}")
                            onSelected(product)
                        }
                    },
                    onPress = {
                        toState = ComponentState.Pressed
                        tryAwaitRelease()
                        toState = ComponentState.Released
                    }
                )
            },
        shape = RoundedCornerShape(25.dp),
        elevation = 5.dp,
    ) {
        Box(Modifier.background(color)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding(horizontal = 20.dp, vertical = 25.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .weight(1f)
                        .background(selectedColor)
                ) {
                    Crossfade(targetState = product.select) { icon ->
                        when (icon) {
                            SelectedState.NotSelected ->
                                Icon(
                                    Icons.Filled.Store,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .align(
                                            Alignment.Center
                                        )
                                        .size(50.dp),
                                    tint = MaterialTheme.colors.secondary
                                )
                            SelectedState.OnSelected ->
                                Icon(
                                    Icons.Filled.Check,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .align(
                                            Alignment.Center
                                        )
                                        .size(50.dp),
                                    tint = Color.White
                                )
                        }

                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(4f)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = product.name,
                        fontFamily = FontFamily(Font(R.font.roboto_black)),
                        fontSize = 18.sp, maxLines = 1
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = formattedDate,
                        fontFamily = FontFamily(Font(R.font.roboto_light))
                    )
                }
                Column(
                    Modifier
                        .fillMaxHeight()
                        .weight(2f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$Currency ${product.price}",
                        fontFamily = FontFamily(Font(R.font.roboto_medium)),
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}
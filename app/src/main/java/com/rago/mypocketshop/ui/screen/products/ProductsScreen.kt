package com.rago.mypocketshop.ui.screen.products

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rago.mypocketshop.R
import com.rago.mypocketshop.data.model.Products
import com.rago.mypocketshop.ui.utils.Currency
import java.text.SimpleDateFormat
import java.util.*


private const val TAG = "ProductsScreen"

enum class ComponentState { Pressed, Released }

enum class SelectedState { OnSelected, NotSelected }

val list = listOf(
    Products(
        name = "Water",
        price = 25.0,
        creationDate = Calendar.getInstance().time
    ),
    Products(
        name = "Coca cola",
        price = 18.0,
        creationDate = Calendar.getInstance().time
    ), Products(
        name = "Pepsi",
        price = 20.0,
        creationDate = Calendar.getInstance().time
    ), Products(
        name = "Big Cola",
        price = 16.0,
        creationDate = Calendar.getInstance().time
    )
)

@Composable
fun ProductsScreen(navController: NavController, viewModel: ProductsViewModel) {
    val listProducts by viewModel.getAllProducts.observeAsState()
    val selectedProduct by viewModel.selectedProduct.observeAsState(false)

    ProductsContent(
        listProducts = listProducts,
        selectedProduct = selectedProduct,
        onBack = { navController.popBackStack() },
        onSelected = {
            viewModel.onSelectedProducts(it)
        },
        onNotSelected = {
            viewModel.onNotSelectedProducts(it)
        })


}

@Preview(showBackground = true)
@Composable
private fun ProductsContent(
    listProducts: List<Products>? = list,
    selectedProduct: Boolean = false,
    onBack: () -> Unit = {},
    onSelected: (Products) -> Unit = {},
    onNotSelected: (Products) -> Unit = {}
) {

    val (newProduct, newProductSetter) = remember {
        mutableStateOf(Products())
    }

    var openDialog by remember {
        mutableStateOf(false)
    }

    if (openDialog) {
        AlertDialog(onDismissRequest = {
            openDialog = false
        },
            title = {
                Text(text = "Nuevo Producto")
            }, text = {
                OutlinedTextField(
                    value = newProduct.name,
                    onValueChange = {
                        newProductSetter(newProduct.copy(name = it))
                    },
                    placeholder = { Text("name") })
            }, confirmButton = {
                TextButton(onClick = {
                    openDialog = false
                }) {
                    Text(text = "Confirm")
                }
            }, dismissButton = {
                TextButton(onClick = { openDialog = false }) {
                    Text(text = "Dismiss")
                }
            })
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { openDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar(cutoutShape = CircleShape) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Row(
                        Modifier
                            .weight(1f), horizontalArrangement = Arrangement.Start
                    ) {
                        Text(text = "hola")
                    }
                    Row(
                        Modifier
                            .weight(1f), horizontalArrangement = Arrangement.End
                    ) {

                        if (selectedProduct) {
                            IconButton(onClick = {}) {
                                Icon(Icons.Filled.Delete, contentDescription = "")
                            }
                        }

                    }
                }
            }
        }) {
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
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSecondary
                        )
                    }
                }
                Text(
                    stringResource(id = R.string.products_title),
                    fontFamily = FontFamily(Font(R.font.roboto_black)),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
            if (listProducts != null) {
                LazyColumn(Modifier.padding(bottom = 60.dp)) {
                    items(listProducts) { product ->
                        ProductsRow(
                            product = product,
                            onSelected = onSelected,
                            onNotSelected = onNotSelected
                        )
                    }
                }
            } else {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun ProductsRow(
    product: Products = list[0],
    onSelected: (Products) -> Unit = {},
    onNotSelected: (Products) -> Unit = {}
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
        targetState = toSelected,
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
                        toSelected = SelectedState.OnSelected
                        onSelected(product)
                    },
                    onPress = {
                        toState = ComponentState.Pressed
                        toSelected = SelectedState.NotSelected
                        onNotSelected(product)
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
                    Crossfade(targetState = toSelected) { icon ->
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
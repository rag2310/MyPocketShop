package com.rago.mypocketshop.ui.screen.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.navigation.NavController
import com.rago.mypocketshop.R
import com.rago.mypocketshop.data.model.Products
import com.rago.mypocketshop.ui.screen.products.components.ProductsRow
import java.util.*


private const val TAG = "ProductsScreen"

enum class ComponentState { Pressed, Released }

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
//    val listProducts by viewModel.listProducts.observeAsState()
    val selectedProduct by viewModel.selectedProduct.observeAsState(false)

    viewModel.getProducts()

    ProductsContent(
        listProducts = listProducts,
        selectedProduct = selectedProduct,
        onBack = { navController.popBackStack() },
        onSelected = {
            viewModel.onSelectedProducts(it)
        },
        onNotSelected = {
            viewModel.onNotSelectedProducts(it)
        }, onDelete = {
            viewModel.deleteProducts()
        }
    ) { product: Products ->
        viewModel.insertProduct(product)
    }
}

@Composable
fun ProductsDialog(closeDialog: () -> Unit = {}, submitDialog: (Products) -> Unit) {
    val (newProduct, newProductSetter) = remember {
        mutableStateOf(Products())
    }

    AlertDialog(onDismissRequest = closeDialog,
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
                submitDialog(newProduct)
                closeDialog()
            }) {
                Text(text = "Confirm")
            }
        }, dismissButton = {
            TextButton(onClick = closeDialog) {
                Text(text = "Dismiss")
            }
        })
}

@Preview(showBackground = true)
@Composable
private fun ProductsContent(
    listProducts: List<Products>? = list,
    selectedProduct: Boolean = false,
    onBack: () -> Unit = {},
    onSelected: (Products) -> Unit = {},
    onNotSelected: (Products) -> Unit = {},
    onDelete: () -> Unit = {},
    onSave: (Products) -> Unit = {}
) {

    var openDialog by remember {
        mutableStateOf(false)
    }

    if (openDialog) {
        ProductsDialog(closeDialog = {
            openDialog = false
        }) { product: Products ->
            onSave(product)
        }
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
                            IconButton(onClick = {
                                onDelete()
                            }) {
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
                            onNotSelected = onNotSelected,
                            selectedProduct = selectedProduct
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
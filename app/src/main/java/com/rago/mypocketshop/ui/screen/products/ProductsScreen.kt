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
import androidx.compose.material.icons.filled.Store
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rago.mypocketshop.R
import com.rago.mypocketshop.data.model.Products
import com.rago.mypocketshop.ui.components.ScaffoldTopBar
import java.text.SimpleDateFormat
import java.util.*

val list = listOf(
    Products(
        name = "Agua Embotellada",
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
    /*ScaffoldTopBar(title = "Productos", navController = navController) {
        ProductsContent(listProducts = listProducts)
    }*/
    Scaffold(
        topBar = {
            TopAppBar {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                    Text(
                        "Productos",
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { println("Add") }) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar(cutoutShape = CircleShape) {
                Text(text = "Hola")
            }
        }) {
        ProductsContent(listProducts = listProducts)
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductsContent(listProducts: List<Products>? = list) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (listProducts != null) {
            LazyColumn {
                items(listProducts) { product ->
                    ProductsRow(product = product)
                }
            }
        } else {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductsRow(product: Products = list[0]) {
    val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH)
    val formattedDate = dateFormat.format(product.creationDate.time)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 5.dp),
        shape = RoundedCornerShape(25.dp),
        elevation = 5.dp
    ) {
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
                    .background(Color.Red.copy(alpha = 0.5f))
            ) {
                Icon(
                    Icons.Filled.Store, contentDescription = null, modifier = Modifier
                        .align(
                            Alignment.Center
                        )
                        .size(50.dp)
                )
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
                    text = "C$ ${product.price}",
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    fontSize = 20.sp,
                    color = Color.Green.copy(alpha = 0.8f)
                )
            }
        }
    }
}
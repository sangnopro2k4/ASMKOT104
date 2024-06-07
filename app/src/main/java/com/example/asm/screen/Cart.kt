package com.example.asm.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.asm.CartItem
import com.example.asm.components.ButtonComponent
import com.example.asm.components.ItemCart

@Composable
fun Cart(
    navController: NavController,
    carts: List<CartItem>,
    handleDelete: (CartItem) -> Unit,
    handleIncrement: (CartItem, quantity: Int) -> Unit,
    handleDecrement: (CartItem, quantity: Int) -> Unit
) {
    val context = LocalContext.current
    var total = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(key1 = carts) {
        total.value = carts.sumOf { it.price * it.quantity }
    }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth()
        ) {
            Image(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier.clickable { navController.popBackStack() })
            Text(
                text = "My cart",
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.width(10.dp))
        }

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 20.dp),
            modifier = Modifier.padding(top = 14.dp)
        ) {
            items(carts) {
                ItemCart(
                    name = it.product_name,
                    price = it.price,
                    url = it.url,
                    quantity = it.quantity,
                    stock = it.stock,
                    handleIncrease = { qtt ->
                        handleIncrement(it, qtt)
                        total.value = carts.sumOf { it.price * it.quantity }
                    },
                    handleDelete = {
                        handleDelete(it)
                        total.value = carts.sumOf { it.price * it.quantity }
                    },
                    handleDecrease = { qtt ->
                        handleDecrement(it, qtt)
                        total.value = carts.sumOf { it.price * it.quantity }
                    }
                )
                Spacer(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(
                            Color(0xFFF0F0F0)
                        )
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        if (total.value > 0) FooterCart(total = total.value, navController = navController)
    }
}

@Composable
fun FooterCart(total: Int, navController: NavController) {
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                value = "",
                modifier = Modifier.weight(1f),
                onValueChange = {}, placeholder = { Text(text = "Enter your promo code") })
            Button(
                onClick = { navController.navigate("Checkout/$total") },
                modifier = Modifier
                    .width(60.dp)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(7.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total: ",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF808080)
            )
            Text(
                text = "$ $total",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        ButtonComponent(text = "Check out", modifier = Modifier.fillMaxWidth()) {
            navController.navigate("Checkout/$total")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CartScreen() {
}
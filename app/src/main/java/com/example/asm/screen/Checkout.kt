package com.example.asm.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.asm.R
import com.example.asm.components.ButtonComponent
import com.example.asm.components.TitleCheckOut

@Composable
fun Checkout(order: Int, navController: NavController) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding()
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", modifier = Modifier.clickable { navController.popBackStack() })
            Text(
                text = "Check out",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.width(1.dp))
        }

        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            TitleCheckOut(title = "Shipping Address")

            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .shadow(10.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)

            ) {
                Text(
                    text = "Bruno Fernandes",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
                )
                Spacer(
                    modifier = Modifier
                        .height(2.dp)
                        .fillMaxWidth()
                        .background(Color(0xFFF0F0F0))
                )
                Text(
                    text = "25 rue Robert Latouche, Nice, 06200, Côte D’azur, France",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
            TitleCheckOut(title = "Payment Method")

            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .shadow(10.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.card),
                        contentDescription = "",
                        modifier = Modifier.width(60.dp)
                    )
                    Text(text = "*** *** *** 3974")
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            TitleCheckOut(title = "Delivery method")
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .shadow(10.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dhl),
                        contentDescription = "",
                        modifier = Modifier.width(60.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Fast (2-3days)")
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
            Column(
                modifier = Modifier
                    .shadow(10.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 20.dp, vertical = 15.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Order: ")
                    Text(text = "$ $order")
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Delivery: ")
                    Text(text = "$ 5.00")
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Total: ")
                    Text(text = "\$ ${order.toDouble() + 5.00}")
                }
            }
            Spacer(modifier = Modifier.height(25.dp))

            ButtonComponent(text = "submit order".uppercase(), modifier = Modifier.fillMaxWidth()) {
                navController.navigate("Success")
            }
        }
    }
}
package com.example.asm.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.asm.R
import com.example.asm.components.ButtonComponent

@Composable
fun SuccessS(navController: NavController) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = "SUCCESS!",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center
        )

        Image(
            painter = painterResource(id = R.drawable.order),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(1f)
                .aspectRatio(1f)
        )

        Text(
            text = "Your order will be delivered soon.\n Thank you for choosing our app!",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        ButtonComponent(text = "Track your orders", modifier = Modifier.fillMaxWidth()) {

        }

        Spacer(modifier = Modifier.height(25.dp))

        ButtonComponent(text = "BACK TO HOME", modifier = Modifier.fillMaxWidth()) {
            navController.navigate("BottomTab") {
                popUpTo("BottomTab") {
                    inclusive = true
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SuccessSPreview() {
}
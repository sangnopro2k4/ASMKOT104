package com.example.asm.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.asm.R
import com.example.asm.components.ButtonComponent

@Composable
fun OnBoarding(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "MAKE YOUR",
                color = Color(0xFF606060),
                fontSize = 24.sp,
                fontFamily = FontFamily.Serif,
            )

            Text(
                text = "HOME BEAUTIFUL",
                color = Color.Black,
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(top = 15.dp)
            )

            Text(
                text = stringResource(id = R.string.onBoarding),
                modifier = Modifier
                    .padding(end = 30.dp, top = 35.dp)
                    .fillMaxWidth(0.8f)
                    .align(alignment = Alignment.End),
                textAlign = TextAlign.Justify,
                fontSize = 15.sp,
                color = Color(0xFF808080),
                lineHeight = 35.sp
            )

            Spacer(modifier = Modifier.fillMaxHeight(0.4f))

            ButtonComponent(
                text = "Get Started",
                onClick = {navController.navigate("Login")},
                textColor = Color.White,
                backgroundColor = Color.Black,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
        }

    }
}
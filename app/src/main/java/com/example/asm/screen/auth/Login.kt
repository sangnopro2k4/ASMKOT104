package com.example.asm.screen.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.asm.api.ViewModel.AuthenticationViewModel
import com.example.asm.components.ButtonComponent
import com.example.asm.components.Logo
import com.example.asm.components.TextButtonComponent
import com.example.asm.components.TextInput

@Composable
fun LoginScreen(navController: NavController, authenticationViewModel: AuthenticationViewModel) {
    val context = LocalContext.current
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val loginResponse by authenticationViewModel.loginResponse
    val loginErrorMessage by authenticationViewModel.loginErrorMessage

    fun login() {
        if (email.isEmpty() || password.isEmpty()) {
            Log.e("Err", "login: email: $email, password: $password", )
            Toast.makeText(context, "all fields are required", Toast.LENGTH_SHORT).show()
        } else {
            authenticationViewModel.login(email, password)
        }
    }

    LaunchedEffect(key1 = loginResponse, key2 = loginErrorMessage) {
        if (loginResponse != null)
            if (loginResponse?.status == true) {
                Toast.makeText(context, loginResponse?.message, Toast.LENGTH_LONG).show()
                navController.navigate("BottomTab") {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            }
        else if (loginErrorMessage.isNotEmpty())
            Toast.makeText(context, loginErrorMessage, Toast.LENGTH_LONG).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 21.dp, horizontal = 15.dp),
    ) {
        Logo(modifier = Modifier.padding(horizontal = 15.dp))
        Column(modifier = Modifier.padding(top = 30.dp, start = 15.dp)) {
            Text(
                text = "Hello !",
                fontFamily = FontFamily.Serif,
                color = Color(0xFF909090),
                fontSize = 30.sp,
                lineHeight = 45.sp
            )
            Text(
                text = "Welcome back".uppercase(),
                fontFamily = FontFamily.Serif,
                color = Color(0xFF303030),
                fontSize = 24.sp,
                lineHeight = 45.sp
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 30.dp)
                .shadow(5.dp)
                .background(Color.White)
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            TextInput(
                value = email,
                label = "Email", modifier = Modifier.padding(top = 30.dp)
            ) {
                email = it
            }
            TextInput(
                value = password,
                label = "Password",
                isPassword = true,
                modifier = Modifier.padding(top = 35.dp)
            ) {
                password = it
            }

            TextButtonComponent(
                text = "Forgot Password",
                modifier = Modifier.padding(top = 38.dp)
            ) {

            }

            ButtonComponent(
                text = "Login", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 39.dp, start = 30.dp, end = 30.dp)
                    .shadow(10.dp)
            ) {
                login()
            }

            TextButtonComponent(
                text = "Sign up".uppercase(),
                modifier = Modifier.padding(top = 30.dp, bottom = 45.dp)
            ) {
                navController.navigate("Register")
            }
        }
    }
}
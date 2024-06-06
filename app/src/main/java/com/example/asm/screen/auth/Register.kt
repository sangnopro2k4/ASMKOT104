package com.example.asm.screen.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.asm.api.ViewModel.AuthenticationViewModel
import com.example.asm.components.ButtonComponent
import com.example.asm.components.Logo
import com.example.asm.components.TextInput

@Composable
fun RegisterScreen(navController: NavController, authenticationViewModel: AuthenticationViewModel) {
    val context = LocalContext.current
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var fullname by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    val registerResponse by authenticationViewModel.registerResponse

    LaunchedEffect(key1 = registerResponse) {
        if (registerResponse != null) {
            if (registerResponse?.status == true) {
                Toast.makeText(context, registerResponse?.message, Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
        }
    }

    fun register() {
        if (email.isEmpty() || password.isEmpty() || fullname.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(context, "all field are required", Toast.LENGTH_SHORT).show()
            return
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(context, "password does not match", Toast.LENGTH_SHORT).show()
            return
        }

        authenticationViewModel.register(email, password, fullname)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = 21.dp,
                horizontal = 15.dp
            ),
    ) {
        Logo(modifier = Modifier.padding(horizontal = 15.dp))
        Text(
            text = "Welcome".uppercase(),
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 30.dp, start = 15.dp)
        )

        Column(
            modifier = Modifier
                .padding(top = 23.dp)
                .shadow(10.dp)
                .background(Color.White)
                .fillMaxWidth()
                .verticalScroll(
                    state = rememberScrollState()
                )
                .padding(horizontal = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextInput(value = fullname, label = "Name", modifier = Modifier.padding(top = 30.dp)) {
                fullname = it
            }

            TextInput(value = email, label = "Email", modifier = Modifier.padding(top = 30.dp)) {
                email = it
            }

            TextInput(
                value = password,
                label = "Password",
                isPassword = true,
                modifier = Modifier.padding(top = 30.dp)
            ) {
                password = it
            }

            TextInput(
                value = confirmPassword,
                label = "Confirm Password",
                isPassword = true,
                modifier = Modifier.padding(top = 30.dp)
            ) {
                confirmPassword = it
            }

            ButtonComponent(
                text = "Sign up".uppercase(),
                modifier = Modifier
                    .padding(top = 50.dp, start = 15.dp, end = 15.dp)
                    .fillMaxWidth()
                    .shadow(10.dp)
            ) {
                register()
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color(0xFF808080))) {
                        append("Already have an account? ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Login".uppercase())
                    }
                },
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 30.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
        }
    }
}
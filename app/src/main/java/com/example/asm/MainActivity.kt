package com.example.asm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.asm.api.ViewModel.AuthenticationViewModel
import com.example.asm.api.ViewModel.CategoryViewModel
import com.example.asm.api.ViewModel.ProductViewModel
import com.example.asm.components.ButtonComponent
import com.example.asm.screen.auth.LoginScreen
import com.example.asm.screen.auth.OnBoarding
import com.example.asm.screen.auth.RegisterScreen
import com.example.asm.screen.tab.BookMarkScreen
import com.example.asm.screen.tab.BottomNav
import com.example.asm.screen.tab.HomeScreen
import com.example.asm.screen.tab.NotificationScreen
import com.example.asm.screen.tab.UserScreen
import com.example.asm.ui.theme.ASMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ASMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val authenticationViewModel = AuthenticationViewModel()
    val categoryViewModel = CategoryViewModel()
    val productViewModel = ProductViewModel()
    ASMTheme {
        Scaffold {
            NavHost(
                modifier = Modifier.padding(it),
                navController = navController,
                startDestination = "Onboarding",
                route = "/"
            ) {
                composable("Onboarding") {
                    OnBoarding(navController)
                }
                composable("Login") {
                    LoginScreen(navController, authenticationViewModel = authenticationViewModel)
                }
                composable("Register") {
                    RegisterScreen(navController, authenticationViewModel = AuthenticationViewModel())
                }

                composable("BottomTab") {
                    BottomTab(navController, categoryViewModel, productViewModel)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ASMTheme {
        MainApp()
    }
}

@Composable
fun BottomTab(_navController: NavController, categoryViewModel: CategoryViewModel, productViewModel: ProductViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNav(navController) }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController, startDestination = "Home"
        ) {
            composable("Home") {
                HomeScreen(_navController, categoryViewModel, productViewModel)
            }

            composable("BookMark") {
                BookMarkScreen()
            }

            composable("Notification") {
                NotificationScreen()
            }

            composable("User") {
                UserScreen()
            }
        }
    }
}
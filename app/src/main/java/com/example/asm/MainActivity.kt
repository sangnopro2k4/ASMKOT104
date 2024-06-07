package com.example.asm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.asm.api.ViewModel.AuthenticationViewModel
import com.example.asm.api.ViewModel.CategoryViewModel
import com.example.asm.api.ViewModel.ProductViewModel
import com.example.asm.screen.Cart
import com.example.asm.screen.Checkout
import com.example.asm.screen.Details
import com.example.asm.screen.SuccessS
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

data class CartItem(
    val id: String,
    val url: Any,
    val product_name: String,
    var quantity: Int,
    val stock: Int,
    val price: Int,
)

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val authenticationViewModel = AuthenticationViewModel()
    val categoryViewModel = CategoryViewModel()
    val productViewModel = ProductViewModel()

    val cart = remember {
        mutableStateListOf<CartItem>()
    }

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
                    RegisterScreen(
                        navController,
                        authenticationViewModel = AuthenticationViewModel()
                    )
                }

                composable("BottomTab") {
                    BottomTab(navController, categoryViewModel, productViewModel) {

                    }
                }

                composable("Detail/{productId}") {
                    val productId = it.arguments?.getString("productId")
                    if (productId != null) {
                        Details(navController, productId, productViewModel, cart) {
                            var existingItem = cart.find { item -> item.id == it.id }
                            if (existingItem != null) {
                                cart.replaceAll { item ->
                                    run {
                                        if (item.id == it.id) {
                                            item.copy(quantity = item.quantity + it.quantity)
                                        } else {
                                            item
                                        }
                                    }
                                }
                            } else
                                cart.add(it)
                        }
                    }
                }

                composable("Cart") {
                    Cart(
                        navController,
                        carts = cart,
                        handleDelete = {
                            cart.remove(it)
                        },
                        handleDecrement = { item, quantity ->
                            run {
                                cart.replaceAll {
                                    if (it.id == item.id) {
                                        it.copy(quantity = quantity)
                                    } else {
                                        it
                                    }
                                }
                            }
                        },
                        handleIncrement = { item, quantity ->
                            run {
                                cart.replaceAll {
                                    if (it.id == item.id) {
                                        it.copy(quantity = quantity)
                                    } else {
                                        it
                                    }
                                }
                            }
                        }
                    )
                }

                composable("Checkout/{price}") {
                    val price = it.arguments?.getString("price")
                    if (price != null) {
                        Checkout(price.toInt(), navController)
                    }

                }

                composable("Success") {
                    SuccessS(navController)
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
fun BottomTab(
    _navController: NavController,
    categoryViewModel: CategoryViewModel,
    productViewModel: ProductViewModel,
    handleAddToCart: (id: String) -> Unit
) {
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
package com.example.asm.screen.tab

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.asm.R

@Composable
fun BottomNav(navController: NavController) {
    val items = listOf(
        Screen.Home,
        Screen.Bookmark,
        Screen.Notification,
        Screen.Profile
    )

    val currentRoute = currentRoute(navController)
    NavigationBar(
        containerColor = Color.White
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = if (currentRoute == screen.route) screen.activeIcon else screen.icon),
                        contentDescription = screen.route
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White
                )
            )
        }
    }
}

sealed class Screen(val route: String, val icon: Int, val activeIcon: Int) {
    object Home : Screen("Home", R.drawable.ic_outline_home, R.drawable.ic_home)
    object Bookmark : Screen("BookMark", R.drawable.ic_bookmark_border, R.drawable.ic_bookmark)
    object Notification :
        Screen("Notification", R.drawable.ic_outline_notifications, R.drawable.ic_notifications)
    object Profile : Screen("User", R.drawable.ic_person_outline, R.drawable.ic_person)
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
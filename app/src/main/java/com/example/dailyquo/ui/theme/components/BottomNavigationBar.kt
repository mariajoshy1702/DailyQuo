package com.example.dailyquo.ui.theme.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dailyquo.navigation.Screen

@Composable
fun BottomNavigationBar(navController: androidx.navigation.NavHostController) {
    val items = listOf(
        Screen.Home,
        Screen.AddQuote,
        Screen.SavedQuotes
    )

    val currentRoute by navController.currentBackStackEntryAsState()

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        items.forEach { screen ->
            val isSelected = currentRoute?.destination?.route == screen.route

            NavigationBarItem(
                label = {
                    Text(
                        screen.route,
                        color = if (isSelected) Color.Gray.copy(alpha = 0.65f)
                        else MaterialTheme.colorScheme.onSecondary
                    )
                },
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navController.navigate(screen.route)
                    }
                },
                icon = {},
                enabled = !isSelected,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Gray.copy(alpha = 0.65f),
                    selectedTextColor = Color.Gray.copy(alpha = 0.65f),
                    unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
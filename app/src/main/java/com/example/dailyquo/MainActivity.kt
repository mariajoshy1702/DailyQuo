package com.example.dailyquo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.dailyquo.data.QuoteDatabase
import com.example.dailyquo.repository.QuoteRepository
import com.example.dailyquo.ui.theme.QuoteViewModel
import com.example.dailyquo.ui.theme.DAILYQUOTheme
import com.example.dailyquo.navigation.NavGraph
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dailyquo.navigation.Screen
import com.example.dailyquo.ui.theme.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = QuoteDatabase.getDatabase(applicationContext)
        val repository = QuoteRepository(database.quoteDao())

        val viewModel =
            ViewModelProvider(this, ViewModelFactory(repository))[QuoteViewModel::class.java]

        setContent {
            DAILYQUOTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = { AppHeader() },
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) { paddingValues ->

                    Box(modifier = Modifier.padding(paddingValues)) {
                        NavGraph(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AppHeader() {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "DAILY QUOTE",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }

    @Composable
    fun BottomNavigationBar(navController: androidx.navigation.NavHostController) {
        val items = listOf(
            Screen.Home,
            Screen.AddQuote,
            Screen.AllQuotes
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
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
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
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        indicatorColor = MaterialTheme.colorScheme.tertiary
                    )
                )
            }
        }
    }
}
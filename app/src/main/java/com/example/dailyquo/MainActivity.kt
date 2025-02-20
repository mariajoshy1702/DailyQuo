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

        // Set up the database and repository
        val database = QuoteDatabase.getDatabase(applicationContext)
        val repository = QuoteRepository(database.quoteDao())

        // Use the ViewModelFactory to create the ViewModel
        val viewModel =
            ViewModelProvider(this, ViewModelFactory(repository))[QuoteViewModel::class.java]

        // Set up the UI
        setContent {
            DAILYQUOTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = { AppHeader() },
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) { paddingValues ->
                    // Pass the paddingValues down to the NavGraph or other composables
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
        CenterAlignedTopAppBar(  // **Creates a centered title in the app bar**
            title = { Text(
                text = "Daily Quote",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )  },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
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

        // Get the current route from the NavController
        val currentRoute by navController.currentBackStackEntryAsState()

        NavigationBar(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) {
            items.forEach { screen ->
                val isSelected = currentRoute?.destination?.route == screen.route

                NavigationBarItem(
                    label = { Text(screen.route) },
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) { // Only navigate if not already on the screen
                            navController.navigate(screen.route)
                        }
                    },
                    icon = {},
                    enabled = !isSelected // Disable if already selected
                )
            }
        }
    }

}
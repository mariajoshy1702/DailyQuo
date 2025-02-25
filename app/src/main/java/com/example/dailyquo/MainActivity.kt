package com.example.dailyquo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.dailyquo.data.room.QuoteDatabase
import com.example.dailyquo.data.api.ApiClient
import com.example.dailyquo.navigation.NavGraph
import com.example.dailyquo.repository.QuoteRepository
import com.example.dailyquo.ui.theme.DAILYQUOTheme
import com.example.dailyquo.ui.theme.QuoteViewModel
import com.example.dailyquo.ui.theme.ViewModelFactory
import com.example.dailyquo.ui.theme.components.AppHeader
import com.example.dailyquo.ui.theme.components.BottomNavigationBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = QuoteDatabase.getDatabase(applicationContext)
        val apiService = ApiClient.retrofit
        val repository = QuoteRepository(database.quoteDao(), apiService)

        val viewModel =
            ViewModelProvider(this, ViewModelFactory(repository))[QuoteViewModel::class.java]

        setContent {
            DAILYQUOTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = { AppHeader() },
                    bottomBar = { BottomNavigationBar(navController) }
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
}

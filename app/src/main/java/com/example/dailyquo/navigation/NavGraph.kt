package com.example.dailyquo.navigation

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dailyquo.ui.theme.QuoteViewModel
import com.example.dailyquo.ui.theme.screens.AddQuoteScreen
import com.example.dailyquo.ui.theme.screens.AllQuotesScreen

sealed class Screen(val route: String) {
    data object Home : Screen("Quote for you")
    data object AddQuote : Screen("Add more")
    data object AllQuotes : Screen("Your quotes")
}

@Composable
fun NavGraph(navController: NavHostController, viewModel: QuoteViewModel) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(viewModel) }
        composable(Screen.AddQuote.route) { AddQuoteScreen(viewModel) }
        composable(Screen.AllQuotes.route) { AllQuotesScreen(viewModel) }
    }
}

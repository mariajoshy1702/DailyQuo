package com.example.dailyquo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dailyquo.ui.theme.QuoteViewModel
import com.example.dailyquo.ui.theme.screens.AddQuoteScreen
import com.example.dailyquo.ui.theme.screens.HomeScreen
import com.example.dailyquo.ui.theme.screens.SavedQuotesScreen

sealed class Screen(val route: String) {
    data object Home : Screen("HOME")
    data object AddQuote : Screen("ADD")
    data object SavedQuotes : Screen("SAVED")
}

@Composable
fun NavGraph(navController: NavHostController, viewModel: QuoteViewModel) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(viewModel) }
        composable(Screen.AddQuote.route) { AddQuoteScreen(viewModel, navController) }
        composable(Screen.SavedQuotes.route) { SavedQuotesScreen(viewModel, navController) }
    }
}

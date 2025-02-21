package com.example.dailyquo.ui.theme.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dailyquo.navigation.Screen
import com.example.dailyquo.ui.theme.QuoteViewModel
import com.example.dailyquo.ui.theme.components.QuoteItem

@Composable
fun AllQuotesScreen(viewModel: QuoteViewModel, navController: NavHostController) {
    BackHandler {
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Home.route) { inclusive = true }
        }
    }
    val quotes by viewModel.allQuotes.collectAsState(initial = emptyList())

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(quotes) { quote ->
            QuoteItem(quote.text, quote.author) {
                viewModel.deleteQuote(quote)
            }
        }
    }
}


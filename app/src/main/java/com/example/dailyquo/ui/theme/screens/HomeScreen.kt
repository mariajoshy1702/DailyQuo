package com.example.dailyquo.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyquo.ui.theme.QuoteViewModel
import com.example.dailyquo.ui.theme.components.QuoteCard

@Composable
fun HomeScreen(viewModel: QuoteViewModel) {
    val quote by viewModel.randomQuote.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchRandomQuote()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        quote?.let {
            QuoteCard(quote = it)
        } ?: Text(text = "Loading...", fontSize = 20.sp)

        Button(
            onClick = { viewModel.fetchRandomQuote() },
            modifier = Modifier
                .padding(16.dp)
                .size(165.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Tap for a\nNew Quote!",
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}


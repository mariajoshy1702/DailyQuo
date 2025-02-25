package com.example.dailyquo.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyquo.R
import com.example.dailyquo.ui.theme.QuoteViewModel
import com.example.dailyquo.ui.theme.components.QuoteCard

@Composable
fun HomeScreen(viewModel: QuoteViewModel) {
    val quote by viewModel.randomQuote.collectAsState()

    LaunchedEffect(Unit) {
        if (quote == null) {
            viewModel.fetchRandomQuote()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.5f))

        quote?.let {
            QuoteCard(quote = it)
        } ?: Text(
            text = stringResource(R.string.loading),
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = { viewModel.fetchRandomQuote() },
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            ) {
                Text(
                    text = stringResource(R.string.tap_for_a_new_quote),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

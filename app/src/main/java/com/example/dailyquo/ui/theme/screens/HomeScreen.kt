package com.example.dailyquo.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.dailyquo.ui.theme.components.CircularProgressIndicator
import com.example.dailyquo.ui.theme.components.CircularProgressIndicatorWithCountdown
import com.example.dailyquo.ui.theme.components.QuoteCard
import com.example.dailyquo.ui.theme.components.ShowTitleText

@Composable
fun HomeScreen(viewModel: QuoteViewModel) {
    val quote by viewModel.randomQuote.collectAsState()
    val errorMessage by viewModel.errorState.collectAsState()

    LaunchedEffect(Unit) {
        if (quote == null) {
            viewModel.fetchRandomQuote()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ShowTitleText(stringResource(R.string.quote_for_you))

        Spacer(modifier = Modifier.weight(0.5f))

        quote?.let {
            QuoteCard(it, viewModel)
        } ?: CircularProgressIndicator()

        if (errorMessage != null) {
            Text(
                text = stringResource(R.string.too_many_requests),
                color = MaterialTheme.colorScheme.error,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
            CircularProgressIndicatorWithCountdown(viewModel)
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { viewModel.fetchRandomQuote() },
            modifier = Modifier
                .size(150.dp)
                .padding(8.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
            enabled = errorMessage == null
        ) {
            Text(
                text = stringResource(R.string.tap_for_a_new_quote),
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

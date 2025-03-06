package com.example.dailyquo.ui.theme.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dailyquo.ui.theme.QuoteViewModel

@Composable
fun CircularProgressIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun CircularProgressIndicatorWithCountdown(viewModel: QuoteViewModel) {
    val timeLeft by viewModel.timeLeft.collectAsState()
    val errorState by viewModel.errorState.collectAsState()

    LaunchedEffect(errorState) {
        if (errorState != null) {
            viewModel.startCountdownExternally()
        }
    }

    if (timeLeft >= 0) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = Color.Gray.copy(alpha = 0.35f),
                modifier = Modifier.size(70.dp),
                trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor
            )

            Text(
                text = "${timeLeft}s",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}



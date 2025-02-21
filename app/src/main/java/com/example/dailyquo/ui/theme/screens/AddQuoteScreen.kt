package com.example.dailyquo.ui.theme.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dailyquo.R
import com.example.dailyquo.data.Quote
import com.example.dailyquo.navigation.Screen
import com.example.dailyquo.ui.theme.QuoteViewModel

@Composable
fun AddQuoteScreen(viewModel: QuoteViewModel, navController: NavHostController) {
    BackHandler {
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Home.route) { inclusive = true }
        }
    }
    var text by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = {
                    Text(
                        stringResource(R.string.quote),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                modifier = Modifier.padding(8.dp),
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground)
            )
            TextField(
                value = author,
                onValueChange = { author = it },
                label = {
                    Text(
                        stringResource(R.string.author),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                modifier = Modifier.padding(8.dp),
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onBackground)
            )
            Button(
                onClick = {
                    if (text.isNotBlank() && author.isNotBlank()) {
                        viewModel.addQuote(Quote(text = text, author = author))
                        Toast.makeText(
                            context,
                            context.getString(R.string.quote_added_successfully),
                            Toast.LENGTH_SHORT
                        ).show()
                        text = context.getString(R.string.blank)
                        author = context.getString(R.string.blank)
                    } else {
                        Toast.makeText(
                            context,
                            context.getString(R.string.please_enter_both_fields),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                enabled = text.isNotBlank() && author.isNotBlank(),
                modifier = Modifier.padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (text.isNotBlank() && author.isNotBlank()) {
                        MaterialTheme.colorScheme.tertiary
                    } else {
                        if (MaterialTheme.colorScheme.background == Color.Black) Color(0xFF555555)
                        else Color.Gray
                    },
                    contentColor = if (text.isNotBlank() && author.isNotBlank()) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        Color.White
                    }
                )
            )

            {
                Text(
                    stringResource(R.string.add_your_quote),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
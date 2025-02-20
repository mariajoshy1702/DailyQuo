package com.example.dailyquo.ui.theme.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.dailyquo.data.Quote
import com.example.dailyquo.ui.theme.QuoteViewModel

@Composable
fun AddQuoteScreen(viewModel: QuoteViewModel) {
    var text by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    val context = LocalContext.current  // Context for showing Toast

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center  // Centers the content
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Quote") },
                modifier = Modifier.padding(8.dp)
            )
            TextField(
                value = author,
                onValueChange = { author = it },
                label = { Text("Author") },
                modifier = Modifier.padding(8.dp)
            )
            Button(
                onClick = {
                    if (text.isNotBlank() && author.isNotBlank()) {
                        viewModel.addQuote(Quote(text = text, author = author))

                        // Show Toast Message
                        Toast.makeText(context, "Quote added successfully!", Toast.LENGTH_SHORT).show()

                        // Clear fields
                        text = ""
                        author = ""
                    } else {
                        Toast.makeText(context, "Please enter both fields!", Toast.LENGTH_SHORT).show()
                    }
                },
                enabled = text.isNotBlank() && author.isNotBlank(),
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Add Your Quote")
            }
        }
    }
}
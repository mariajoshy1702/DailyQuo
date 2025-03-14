package com.example.dailyquo.ui.theme.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dailyquo.R
import com.example.dailyquo.data.room.Quote
import com.example.dailyquo.navigation.Screen
import com.example.dailyquo.ui.theme.QuoteViewModel
import com.example.dailyquo.ui.theme.components.GetLeftCanvas
import com.example.dailyquo.ui.theme.components.QuoteItem
import com.example.dailyquo.ui.theme.components.ScrollBar
import com.example.dailyquo.ui.theme.components.ShowTitleText
import com.example.dailyquo.ui.theme.components.showToastMessage

@Composable
fun SavedQuotesScreen(viewModel: QuoteViewModel, navController: NavHostController) {
    BackHandler {
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Home.route) { inclusive = true }
        }
    }

    val quotes by viewModel.savedQuotes.collectAsState(initial = emptyList())
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    val (quoteToDelete, setQuoteToDelete) = remember { mutableStateOf<Quote?>(null) }
    val context = LocalContext.current
    val lazyListState = rememberLazyListState()

    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary
    )

    if (showDialog && quoteToDelete != null) {
        AlertDialog(
            onDismissRequest = { setShowDialog(false) },
            title = { Text(text = stringResource(R.string.delete_quote_icon)) },
            text = { Text(text = stringResource(R.string.deletion_confirmation)) },
            confirmButton = {
                Button(
                    onClick = {
                        quoteToDelete.let { viewModel.deleteQuote(it) }
                        setShowDialog(false)
                        showToastMessage(context, context.getString(R.string.quote_deleted))
                    },
                    colors = buttonColors
                ) {
                    Text(stringResource(R.string.yes))
                }
            },
            dismissButton = {
                Button(
                    onClick = { setShowDialog(false) },
                    colors = buttonColors
                ) {
                    Text(stringResource(R.string.no))
                }
            }
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {

            ShowTitleText(stringResource(R.string.saved_quotes))

        }

        Box {
            if (quotes.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 20.dp, end = 10.dp)
                ) {
                    GetLeftCanvas()

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        ),
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 16.dp,
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.empty_error_message),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Cursive,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSecondary,
                            lineHeight = 35.sp,
                            modifier = Modifier.padding(horizontal = 30.dp, vertical = 8.dp)
                        )
                    }
                }
            } else {
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 16.dp
                    )
                ) {
                    items(quotes) { quote ->
                        QuoteItem(
                            text = quote.text,
                            author = quote.author,
                            onDeleteClick = {
                                setQuoteToDelete(quote)
                                setShowDialog(true)
                            },
                            onCopyClick = {
                                val clipboardManager = context.getSystemService(android.content.ClipboardManager::class.java)
                                val clipData = android.content.ClipData.newPlainText("Quote: ", "\"" + quote.text + "\"" + "\n-" + quote.author)
                                clipboardManager.setPrimaryClip(clipData)
                                showToastMessage(context,
                                    context.getString(R.string.copied_to_clipboard))
                            }
                        )

                    }
                }
                ScrollBar(lazyListState = lazyListState)
            }
        }
    }
}
